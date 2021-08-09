package com.shopping.cart.service;

import com.shopping.cart.dto.CartDTO;

public interface CartService {

    CartDTO addProduct(Long personID, Long productID, Integer quantity);

}

