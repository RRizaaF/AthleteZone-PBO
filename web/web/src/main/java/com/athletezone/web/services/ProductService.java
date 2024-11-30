package com.athletezone.web.services;


import com.athletezone.web.dto.ProductDTO;
import com.athletezone.web.models.Product;
import com.athletezone.web.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Mendapatkan semua produk dari database
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Mendapatkan produk berdasarkan ID
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // Menyimpan produk baru
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    // Menghapus produk berdasarkan ID
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    // Custom Query: Mencari produk berdasarkan nama
    public List<Product> searchProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    public List<ProductDTO> getProductsByCategory(String category) {
        List<Product> products = productRepository.findByCategory(category);
        return products.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Custom Query: Filter berdasarkan kategori dan harga
    public List<Product> getProductsByCategoryAndMaxPrice(String category, double maxPrice) {
        return productRepository.findByCategoryAndPriceLessThanEqual(category, maxPrice);
    }

    private ProductDTO convertToDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .brand(product.getBrand())
                .price(product.getPrice())
                .photoUrl(product.getPhotoUrl())
                .category(product.getCategory())
                .build();
    }
}
