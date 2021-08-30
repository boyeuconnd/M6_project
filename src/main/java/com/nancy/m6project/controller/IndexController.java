package com.nancy.m6project.controller;


import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("index")
    @ApiOperation("Trang chủ của BE API")
    public String getIndexPage(){
        return "index";
    }
}
