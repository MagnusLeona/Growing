package com.magnus.managee.main.business.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Redirect {

    @RequestMapping("/jsp")
    public String getJsp(Model model) {
        model.addAttribute("message", "this is message");
        return "/hello.jsp";
    }

    @RequestMapping("/forward")
    public String forward(Model model) {
        model.addAttribute("message", "this is message");
        return "forward:/index.jsp";
    }

    @RequestMapping("/redirect")
    public String redirect(Model model) {
        return "redirect:/index.jsp";
    }
}
