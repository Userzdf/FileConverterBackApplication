package com.example.fileconverterback.tools;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class CommonUtils {
    private Map<Integer,String> fileTypes = new HashMap<>();

    public Map<Integer,String> getFileTypes() {
        Map<Integer,String> fileType = new HashMap();
        fileType.put(1,".doc");
        fileType.put(2,".docx");
        fileType.put(3,".xlsx");
        fileType.put(4,".pdf");
        return this.fileTypes = fileType;
    }

    public static void main(String[] args) {
        System.out.println(new CommonUtils().getFileTypes());
    }
}
