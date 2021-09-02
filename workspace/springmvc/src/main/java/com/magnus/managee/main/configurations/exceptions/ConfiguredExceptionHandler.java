package com.magnus.managee.main.configurations.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ConfiguredExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    public String get(NullPointerException e) throws Exception {
//        return "An Error occurred";
        System.out.println("@ExceptionHandler");
//        throw new Exception("aaa");
        return "Resolved";

    }
}
