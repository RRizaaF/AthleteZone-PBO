package com.athletezone.web.controller;

import com.athletezone.web.dto.ProductDTO;
import com.athletezone.web.services.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import com.athletezone.web.models.Cart;
import com.athletezone.web.services.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class U_cartController {
    private final CartService cartService;
    private final ProductService productService;

    public U_cartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @GetMapping("/U_cart")
    public String viewCart(Model model, HttpSession session) {
        // Periksa apakah id user ada di session
        Long userIdLong = (Long) session.getAttribute("userId");
        if (userIdLong != null) {
            // Konversi dari Long ke Integer
            Integer userId = userIdLong.intValue();
            Cart cart = cartService.getCartByUserId(Long.valueOf(userId));

            // Ambil semua produk
            List<ProductDTO> allProducts = productService.getAllProducts();
            // Ambil produk yang ada di cart
            List<Long> productIdsInCart = cart.getCartItems().stream()
                    .map(item -> item.getProduct().getId())
                    .toList();

            // Filter produk yang belum ada di cart
            List<ProductDTO> filteredProducts = allProducts.stream()
                    .filter(product -> !productIdsInCart.contains(product.getId()))
                    .toList();

            // Batasi hanya 3 produk
            List<ProductDTO> limitedProducts = filteredProducts.stream().limit(3).toList();

            // Kirim data ke view
            model.addAttribute("limitedProducts", limitedProducts);
            model.addAttribute("cart", cart);
            model.addAttribute("cartItems", cart.getCartItems());
            return "U_cart";
        }
        return "redirect:/login"; // Redirect ke login jika session tidak valid
    }

}

