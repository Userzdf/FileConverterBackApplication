package com.example.fileconverterback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class FileConverterBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileConverterBackApplication.class, args);
    }

}
