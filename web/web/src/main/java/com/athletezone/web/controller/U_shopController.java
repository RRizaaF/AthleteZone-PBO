package com.athletezone.web.controller;

import com.athletezone.web.dto.ProductDTO;
import com.athletezone.web.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class U_shopController {
    private final ProductService productService;
    public U_shopController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/U_shop")
    public String showShopPage(Model model) {
        List<ProductDTO> products = productService.getAllProducts();
        model.addAttribute("products", products); // Menambahkan data produk ke model
        return "U_shop";
    }
}