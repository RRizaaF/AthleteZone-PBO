package com.athletezone.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class U_paymentController {
    @GetMapping("U_payment")
    public String payment() {
        return "U_payment";
    }
}