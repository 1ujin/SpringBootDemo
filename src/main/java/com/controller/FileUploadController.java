package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pojo.User;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Api(tags = "上传文件")
@Controller
public class FileUploadController {
    @GetMapping("/upload")
    public String fileUpload() {
        return "upload";
    }

    @ResponseBody
    // 此处可以直接用 PostMapping
    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    // 可以使用一个POJO来包装RequestParam所注释的参数，这个POJO本身没有要求额外的注解，但是POJO本身必须包含和请求参数完全匹配的字段，标准的setter/getter，和一个无参的构造器 https://www.jianshu.com/p/b4c8d8de4dad
    public String fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        File convertFile = new File(file.getOriginalFilename());
        System.out.println(file.getOriginalFilename());
        convertFile.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(convertFile);
        fileOutputStream.write(file.getBytes());
        fileOutputStream.close();
        return "Upload successfully.";
    }

    @ResponseBody
    @PostMapping("/multi_upload")
    public String fileUpload(@RequestParam("file1") MultipartFile file1, @RequestPart User user, @RequestParam("file2") MultipartFile file2) {
        System.out.println(file1.getOriginalFilename());
        System.out.println(user);
        System.out.println(file2.getOriginalFilename());
        return "Upload successfully.";
    }

    @ResponseBody
    @PostMapping("/upload_user")
    public String fileUpload(User user) {
        System.out.println(user);
        return "Upload successfully.";
    }
}
