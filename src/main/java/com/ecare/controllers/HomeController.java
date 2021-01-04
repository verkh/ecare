package com.ecare.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String getHome() {
        return "index";
    }

    @RequestMapping(value = "/About")
    public String getAbout() {
        return "About";
    }
}