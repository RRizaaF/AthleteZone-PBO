package com.athletezone.web.services.impl;

import com.athletezone.web.models.Cart;
import com.athletezone.web.models.CartItem;
import com.athletezone.web.models.Product;
import com.athletezone.web.repositories.CartItemRepository;
import com.athletezone.web.repositories.CartRepository;
import com.athletezone.web.repositories.ProductRepository;
import com.athletezone.web.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void addToCart(Long userId, Long productId, int quantity) {
        Cart cart = cartRepository.findByUserId(userId);
        Product product = productRepository.findById(productId).orElseThrow();

        CartItem cartItem = CartItem.builder()
                .cart(cart)
                .product(product)
                .quantity(quantity)
                .subTotal(quantity * product.getPrice())
                .build();

        cartItemRepository.save(cartItem);

        cart.setTotalPrice(cart.getTotalPrice() + cartItem.getSubTotal());
        cartRepository.save(cart);
    }
}
