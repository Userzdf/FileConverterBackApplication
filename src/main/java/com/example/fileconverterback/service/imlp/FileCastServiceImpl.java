package com.example.fileconverterback.service.imlp;

import com.example.fileconverterback.core.ConverterFile;
import com.example.fileconverterback.core.FileType;
import com.example.fileconverterback.core.Result;
import com.example.fileconverterback.dto.config.PdfCastVo;
import com.example.fileconverterback.dto.config.SystemConfig;
import com.example.fileconverterback.service.FileCastService;
import com.example.fileconverterback.tools.CommonUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FileCastServiceImpl implements FileCastService {
    @Resource
    ConverterFile converterFile;
    @Resource
    SystemConfig systemConfig;
    @Resource
    PdfCastVo pdfCastVo;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    @Transactional
    public Result pdfCast(MultipartFile file,byte fileType) throws Exception{
        Result result = Result.newInstance();
        try {
            //1、先把上传文件保存到本地临时目录，所有操作必须保持原子性、一致性、持久性
            String fileName = file.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            if(!".pdf".equals(suffixName)){
                return result.failure("非法的文档类型，仅支持pdf,当前是："+"["+suffixName+"]");
            }
            fileName = systemConfig.getFileName()+suffixName;
            String onServerPath = systemConfig.getTempPath()+fileName;

            File onSave = new File(onServerPath);
            if(!onSave.getParentFile().exists()){
                onSave.getParentFile().mkdir();
            }
            file.transferTo(onSave);
            //2、开始转换，这种看着比较复杂，但是代码整洁
            Map<Integer,String> fileTypes = new CommonUtils().getFileTypes();
            for (Map.Entry<Integer, String> entry : fileTypes.entrySet()) {
                if (entry.getKey() == fileType){
                    systemConfig.setFileType(entry.getValue());
                    converterFile.convertPdfToWord(onSave.getAbsolutePath().toString(), systemConfig.getWordFilePath());
                }
            }
            pdfCastVo.setOnServerPath(onSave.getAbsolutePath());
            pdfCastVo.setProxyServerPath(systemConfig.getProxyPath());
            redisTemplate.opsForList().leftPush("pdfCastVo",pdfCastVo);
            Map<String,Object> map = new HashMap<>();
            map.put("pdfCastVo",pdfCastVo);
            return result.success(map);
        }catch (Exception e){
            result.setData(e);
            return result.failure();
        }
    }
}
