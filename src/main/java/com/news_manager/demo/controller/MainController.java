package com.news_manager.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Dmitriy on 29.05.2018.
 */
@Controller
public class MainController {
    @RequestMapping("/")
    public String welcome() {
        return "index.html";
    }
}
