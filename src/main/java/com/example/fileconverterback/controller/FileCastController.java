package com.example.fileconverterback.controller;

import com.example.fileconverterback.core.Result;
import com.example.fileconverterback.service.FileCastService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/file")
@CrossOrigin
public class FileCastController {
    @Resource
    FileCastService fileCastService;

    /**
     * @param file
     * @param response
     * @param fileType 1、word，2、excel
     * @return
     * @throws Exception
     */
    @RequestMapping("/pdfCast")
    public Result pdfCastWord(@RequestParam("file") MultipartFile file, HttpServletResponse response,byte fileType) throws Exception{
        return fileCastService.pdfCast(file,fileType);
    }
}
