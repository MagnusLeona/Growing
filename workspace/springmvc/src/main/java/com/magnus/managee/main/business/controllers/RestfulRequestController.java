package com.magnus.managee.main.business.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 设计Restful风格的请求： Restful风格是，请求的参数做成链接中的形式，从链接?参数 的形式变成 链接/参数值这种。
 */

@RestController
public class RestfulRequestController {

    @GetMapping("/rest/{a}/{b}")
    public String get(@PathVariable int a, @PathVariable int b) throws IOException {
        return "Get---Result: " + a + b;
    }

    @PostMapping("/rest/{a}/{b}")
    public String post(@PathVariable int a, @PathVariable int b) {
        return "Post---Result: " + a + b;
    }
}
