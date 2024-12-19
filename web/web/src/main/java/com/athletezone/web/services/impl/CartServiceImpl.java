package com.athletezone.web.services.impl;

import com.athletezone.web.models.Cart;
import com.athletezone.web.models.CartItem;
import com.athletezone.web.models.Product;
import com.athletezone.web.models.User;
import com.athletezone.web.repositories.CartItemRepository;
import com.athletezone.web.repositories.CartRepository;
import com.athletezone.web.repositories.ProductRepository;
import com.athletezone.web.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    @Override
    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    @Override
    public List<CartItem> getCartItemsByUserId(Long userId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            throw new IllegalArgumentException("Cart not found for user ID: " + userId);
        }
        return cartItemRepository.findByCartId(cart.getId());
    }


    @Override
    public void addToCart(Long userId, Long productId) {
        // Cari cart berdasarkan user
        Cart cart = cartRepository.findByUserId(userId);

        // Cari produk berdasarkan ID
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Periksa apakah produk sudah ada di cart items
        Optional<CartItem> existingCartItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (existingCartItem.isPresent()) {
            // Jika produk sudah ada, tambahkan quantity
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            cartItem.setSubTotal(cartItem.getQuantity() * product.getPrice());
        } else {
            // Jika produk belum ada di keranjang, buat item baru
            CartItem newCartItem = CartItem.builder()
                    .cart(cart)
                    .product(product)
                    .quantity(1)
                    .subTotal(product.getPrice())
                    .build();
            cart.getCartItems().add(newCartItem);
        }

        // Hitung ulang total harga
        double totalPrice = cart.getCartItems().stream()
                .mapToDouble(CartItem::getSubTotal)
                .sum();
        cart.setTotalPrice(totalPrice);

        // Simpan cart
        cartRepository.save(cart);
    }

    @Override
    public void addProductToCart(Long userId, Long productId) {
        // Cari cart berdasarkan user ID
        Cart cart = cartRepository.findByUserId(userId);


        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));



        // Periksa apakah produk sudah ada di cart
        Optional<CartItem> existingCartItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (existingCartItem.isPresent()) {
            // Jika produk sudah ada, tambahkan quantity
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            cartItem.setSubTotal(cartItem.getQuantity() * product.getPrice());
        } else {
            // Jika produk belum ada, tambahkan item baru
            CartItem newCartItem = CartItem.builder()
                    .cart(cart)
                    .product(product)
                    .quantity(1)
                    .subTotal(product.getPrice())
                    .build();
            cart.getCartItems().add(newCartItem);
        }

        // Update total harga cart
        double totalPrice = cart.getCartItems().stream()
                .mapToDouble(CartItem::getSubTotal)
                .sum();
        cart.setTotalPrice(totalPrice);

        // Simpan perubahan pada cart
        cartRepository.save(cart);
    }


}