package com.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

@RestController
public class FileDownloadController {
    @RequestMapping(value = "/download/{filename}", method = RequestMethod.GET)
    public ResponseEntity<Object> fileDownload(@PathVariable(name = "filename") String filename) throws FileNotFoundException, UnsupportedEncodingException {
        System.out.println("下载文件: " + filename);
        // 项目所在路径下
        File file = new File(filename);
        InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(file), "utf-8");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", new String(file.getName().getBytes("UTF-8"), "iso-8859-1")));
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        ResponseEntity<Object> responseEntity =ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(MediaType.parseMediaType("multipart/form-data;charset=UTF-8")).body(inputStreamResource);
        return responseEntity;
    }
}
