package com.athletezone.web.controller;

import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class U_homepageController {

    @GetMapping("/U_homepage")
    public String homepage(HttpSession session, Model model) {
        // Periksa apakah id user ada di session
        Long userIdLong = (Long) session.getAttribute("userId");
        if (userIdLong != null) {
            // Konversi dari Long ke Integer
            Integer userId = userIdLong.intValue();

            // Kirim id user ke view
            model.addAttribute("userId", userId);
            return "U_homepage"; // Tampilkan halaman
        }
        return "redirect:/login"; // Redirect ke login jika session tidak valid
    }
}