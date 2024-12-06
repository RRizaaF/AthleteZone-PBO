package com.athletezone.web.services.impl;

import com.athletezone.web.dto.ProductDTO;
import com.athletezone.web.models.Product;
import com.athletezone.web.repositories.ProductRepository;
import com.athletezone.web.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private static final String UPLOAD_DIR = "web/storage/";

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    // Mendapatkan semua produk dari database
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public String saveFile(MultipartFile file) throws IOException {
        Path uploadPath = Paths.get(UPLOAD_DIR);

        // Buat folder jika belum ada
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Buat nama unik untuk file
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        // Path lengkap file
        Path filePath = uploadPath.resolve(fileName);
        // Simpan file ke direktori
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        // Return path relatif
        return filePath.toString();
    }

    @Override
    // Menyimpan produk baru
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    // Menghapus produk berdasarkan ID
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    private ProductDTO convertToDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .brand(product.getBrand())
                .price(product.getPrice())
                .photoUrl(product.getPhotoUrl())
                .category(product.getCategory())
                .stock(product.getStock())
                .build();
    }
}
