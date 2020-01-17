package com.imooc.web.controller;

import com.imooc.dto.FileInfo;
import jdk.internal.util.xml.impl.Input;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * lcd  2020/1/4
 * Description:
 */
@RestController
@RequestMapping("/file")
public class FileController {
    File path = new File("C:\\javaCode\\security-package\\security-demo\\src\\main\\java\\com\\imooc\\web\\controller");

    @PostMapping
    public FileInfo upload(MultipartFile file) throws IOException {
        File dest = new File(path, System.currentTimeMillis() + ".txt");
        file.transferTo(dest);
        System.out.println(file.getContentType());
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());
        System.out.println(file.getClass());
        return new FileInfo(dest.getAbsolutePath());
    }

    @GetMapping("/{id}")
    public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        File src = new File(path, id + ".txt");
        try (
                InputStream is = new FileInputStream(src);
                OutputStream os=response.getOutputStream();
        ) {
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition","attachment;filename=text.txt");
            IOUtils.copy(is, os);
            os.flush();
        }catch (Exception e){
            throw new RuntimeException();
        }
    }
}
