package com.shopping.cart.service;

import com.shopping.cart.dto.CartDTO;

public interface CartService {

    CartDTO fetchCart(Long personId);

    CartDTO addProduct(Long personID, Long productId, Integer quantity);

    CartDTO updateCartItem(Long personID, Long cartItemId, Integer quantity);

}

