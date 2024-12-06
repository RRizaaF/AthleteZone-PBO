package com.athletezone.web.controller;

import com.athletezone.web.models.Product;
import com.athletezone.web.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import com.athletezone.web.dto.ProductDTO;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class A_dashboardController {
    private final ProductService productService;

    public A_dashboardController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/A_dashboard")
    public String showDashboard(Model model) {
        List<ProductDTO> products = productService.getAllProducts();
        model.addAttribute("products", products);

        Product product = new Product();
        model.addAttribute("product", product);
        return "A_dashboard";
    }

    @PostMapping("/A_dashboard")
    public String addProduct(@ModelAttribute("product") Product product, @RequestParam("photo") MultipartFile file, Model model) {
        try {
            // Simpan file dan dapatkan path-nya
            String filePath = productService.saveFile(file);
            // Set path ke objek Product
            product.setPhotoUrl(filePath);

            productService.saveProduct(product);
            model.addAttribute("successMessage", "Product successfully added!");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Failed to add product. Please try again.");
        }

        // Redirect kembali ke halaman library
        return "redirect:/A_dashboard";
    }
}
