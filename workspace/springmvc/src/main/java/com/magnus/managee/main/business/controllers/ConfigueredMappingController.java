package com.magnus.managee.main.business.controllers;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ConfigueredMappingController {

    @ResponseBody
    public String index(HttpServletResponse httpServletResponse) throws IOException {
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        outputStream.println("Hello this is ur own configuerer");
        System.out.println("ConfiguredMappingHandler");
        return "aaaaa";
    }
}
