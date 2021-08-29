package com.controller;

import io.swagger.annotations.Api;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Api(tags = "下载文件")
@RestController
public class FileDownloadController {
    // 此处可以直接用 GetMapping
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

        ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(MediaType.parseMediaType("multipart/form-data;charset=UTF-8")).body(inputStreamResource);
        return responseEntity;
    }

    // Request -> filename -> file -> FileInputStream -> byte[] -> ZipOutputStream -> ByteArrayOutputStream -> Response
    @GetMapping(value = "/downloadZip", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> zipDownload(String[] filenames, String zipName) {
        byte[] buf = new byte[1024];
        // 获取输出流
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            // ZipOutputStream类：完成文件或文件夹的压缩
            ZipOutputStream out = new ZipOutputStream(bos);
            for (String filename : filenames) {
                // 此处可用任意其他输入流将FileInputStream取代,outputStream为其他步骤的输出流
                // ByteArrayInputStream in = new ByteArrayInputStream(outputStream.toByteArray());
                FileInputStream in = new FileInputStream(new File(filename));
                // 给列表中的文件单独命名
                out.putNextEntry(new ZipEntry(filename));
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                out.closeEntry();
                in.close();
            }
            out.close();
            bos.close();
            System.out.println("压缩完成.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 设置http响应头
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=" + zipName + ".zip");
        // return new ResponseEntity<byte[]>(bos.toByteArray(), header, HttpStatus.CREATED);
        return ResponseEntity.ok().headers(headers).contentLength(bos.size()).contentType(MediaType.parseMediaType("multipart/form-data;charset=UTF-8")).body(bos.toByteArray());
    }
}
