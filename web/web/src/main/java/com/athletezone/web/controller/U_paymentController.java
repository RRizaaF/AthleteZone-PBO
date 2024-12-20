package com.athletezone.web.controller;

import com.athletezone.web.models.Cart;
import com.athletezone.web.models.User;
import com.athletezone.web.services.CartService;
import com.athletezone.web.services.ProductService;
import com.athletezone.web.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class U_paymentController {
    private final CartService cartService;
    private final UserService userService;

    public U_paymentController(CartService cartService, UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }

    @GetMapping("U_payment")
    public String showPayment(Model model, HttpSession session) {
        // Periksa apakah id user ada di session
        Long userIdLong = (Long) session.getAttribute("userId");
        if (userIdLong != null) {
            // Konversi dari Long ke Integer
            Integer userId = userIdLong.intValue();
            Cart cart = cartService.getCartByUserId(Long.valueOf(userId));
            User user = userService.getUserById(userIdLong);
            // Kirim id user ke view
            model.addAttribute("user", user);
            model.addAttribute("cart", cart);
            model.addAttribute("cartItems", cart.getCartItems());
            return "U_payment";
        }
        return "redirect:/login"; // Redirect ke login jika session tidak valid
    }
}