package com.controller;

import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Api(tags = "上传文件")
@RestController
public class FileUploadController {
    // 此处可以直接用 PostMapping
    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    // 可以使用一个POJO来包装RequestParam所注释的参数，这个POJO本身没有要求额外的注解，但是POJO本身必须包含和请求参数完全匹配的字段，标准的setter/getter，和一个无参的构造器 https://www.jianshu.com/p/b4c8d8de4dad
    public String fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        File convertFile = new File(file.getOriginalFilename());
        System.out.println(file.getOriginalFilename());
        convertFile.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(convertFile);
        fileOutputStream.write(file.getBytes());
        fileOutputStream.close();
        return "Upload successfully.";
    }
}
