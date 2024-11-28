package com.athletezone.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class U_shopController {
    @GetMapping("/U_shop")
    public String showShopPage() {
        return "U_shop";
    }
}
