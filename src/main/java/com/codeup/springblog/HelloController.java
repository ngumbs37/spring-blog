package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {

    @GetMapping("/")
    @ResponseBody
    public String hello(){
        return "This is the landing page!";
    }



}