package com.athletezone.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class U_product {
    @GetMapping("U_product")
    public String product() {
        return "U_product";
    }
}