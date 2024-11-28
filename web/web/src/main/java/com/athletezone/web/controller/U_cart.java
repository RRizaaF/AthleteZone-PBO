package com.athletezone.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class U_cart {
    @GetMapping("U_cart")
    public String cart() {
        return "U_cart";
    }
}