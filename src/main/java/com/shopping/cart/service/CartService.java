package com.shopping.cart.service;

import com.shopping.cart.dto.CartDTO;

public interface CartService {

    CartDTO fetchCart();

    CartDTO addProduct(Long productId, Integer quantity);

    CartDTO updateCart(CartDTO cartDTO);

    CartDTO updateCartItem(Long cartItemId, Integer quantity);

    CartDTO deleteCartItem(Long cartItemId);

    void emptyCart();

}

