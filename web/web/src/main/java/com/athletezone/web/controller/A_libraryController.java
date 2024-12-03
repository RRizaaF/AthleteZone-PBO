package com.athletezone.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class A_libraryController {
    @GetMapping("A_library")
    public String library() {
        return "A_library";
    }
}
