package com.athletezone.web.controller;

import com.athletezone.web.dto.ProductDTO;
import com.athletezone.web.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class U_productController {

    private final ProductService productService;

    public U_productController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/U_product/{id}")
    public String showProductDetails(@PathVariable Long id, Model model) {
        // Ambil data produk berdasarkan ID
        ProductDTO product = productService.getProductById(id);

        // Tambahkan produk ke model
        model.addAttribute("product", product);

        // Return halaman U_product.html
        return "U_product";
    }

}
