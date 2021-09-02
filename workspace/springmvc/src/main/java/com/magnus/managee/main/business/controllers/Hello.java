package com.magnus.managee.main.business.controllers;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Hello implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        System.out.println("Hello xml method invoked");
//        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
//        outputStream.println("Controller based on xml");
        ModelAndView modelAndView = new ModelAndView("hello");
        modelAndView.addObject("message", "this is a message");
        return modelAndView;
    }
}
