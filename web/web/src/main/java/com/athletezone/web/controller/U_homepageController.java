package com.athletezone.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class U_homepageController {
    @GetMapping("/")
    public String homepage() {
        return "U_homepage";
    }
}
