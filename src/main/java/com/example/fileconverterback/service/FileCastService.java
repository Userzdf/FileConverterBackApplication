package com.example.fileconverterback.service;

import com.example.fileconverterback.core.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileCastService {
    Result pdfCast(MultipartFile file,byte fileType) throws Exception;
}
