package com.magnus.managee.main.business.controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class HelloWorld {

    @RequestMapping(path = "/hello",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void index(HttpServletResponse httpServletResponse) throws IOException {
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        outputStream.println("Hello world, this is a start for springmvc");
//        return "this is response body";
    }
}
