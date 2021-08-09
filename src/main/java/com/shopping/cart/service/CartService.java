package com.shopping.cart.service;

import com.shopping.cart.dto.CartDTO;
import com.shopping.cart.entity.Cart;

import java.util.List;

public interface CartService {

    CartDTO addProduct(Long personID, Long productID, Integer quantity);

    /*Cart addCart(long personId);

    List<Cart> getAllCarts(Long personId);

    Cart addProductToCart(Long chartId, Long productId, Integer quantity);

    Cart deleteProductFromCart(Long chartId, Long productId);

    String deleteById(Long id);*/
}

