package com.athletezone.web.services;


import com.athletezone.web.dto.ProductDTO;
import com.athletezone.web.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service
@Transactional
public interface ProductService {

    // Mendapatkan semua produk dari database
    public List<ProductDTO> getAllProducts();

    //Menyimpan file gambar produk ke dalam database
    public String saveFile(MultipartFile file) throws IOException;

    // Menyimpan produk baru
    public Product saveProduct(Product product);

    // Menghapus produk berdasarkan ID
    public void deleteProductById(Long id);

}
