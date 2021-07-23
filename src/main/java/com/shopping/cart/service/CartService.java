package com.shopping.cart.service;

import com.shopping.cart.entity.Cart;

import java.util.List;

public interface CartService {

    Cart addCart(long personId);

    List<Cart> getAllCarts(Long personId);

    Cart addProductToCart(Long chartId, Long productId, Integer quantity);

    Cart deleteProductFromCart(Long chartId, Long productId);

    String deleteById(long id);
}

