package com.athletezone.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class A_dashboardController {
    @GetMapping("A_dashboard")
    public String dashboard() {
        return "A_dashboard";
    }
}