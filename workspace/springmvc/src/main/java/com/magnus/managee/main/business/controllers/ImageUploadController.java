package com.magnus.managee.main.business.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
public class ImageUploadController {

    @PostMapping ("/imgupload")
    public void index(@RequestPart("picture") MultipartFile multipartFile, HttpServletResponse httpServletResponse) throws IOException {
        MultipartFile multipartFile1 = multipartFile;

        System.out.println(multipartFile);

        byte[] bytes = multipartFile.getBytes();


        // 保存图片到本地，后期考虑改造成保存图片到对象存储服务器
        File file = new File("C://tmp/my.jpg");

        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(bytes);
        fileOutputStream.flush();
        fileOutputStream.close();

        httpServletResponse.getOutputStream().println("Upload succeed!");
    }
}
