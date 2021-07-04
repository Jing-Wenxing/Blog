package com.blogs.blogend.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class Index {
    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello(){
        return "QingYUS Blogs.";
    }
}