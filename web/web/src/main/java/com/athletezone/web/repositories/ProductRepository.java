package com.athletezone.web.repositories;

import com.athletezone.web.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long> {

    // Custom query: Cari produk berdasarkan nama
    List<Product> findByNameContainingIgnoreCase(String name);

    // Custom query: Cari produk berdasarkan brand
    List<Product> findByBrand(String brand);

    // Custom query: Cari produk berdasarkan kategori
    List<Product> findByCategory(String category);

    // Custom query: Cari produk dengan harga kurang dari atau sama dengan nilai tertentu
    List<Product> findByPriceLessThanEqual(double price);

    // Custom query: Cari produk dengan kombinasi kategori dan harga
    List<Product> findByCategoryAndPriceLessThanEqual(String category, double price);

}
