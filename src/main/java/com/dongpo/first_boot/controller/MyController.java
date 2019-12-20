package com.dongpo.first_boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {

    @RequestMapping("/helloTest")
    public String testRequest(){
        return "hello";
    }
}
