package com.shopping.cart.validator;

import com.shopping.cart.exceptions.CartException;
import com.shopping.cart.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartValidator {

    @Autowired
    private CartRepository cartRepository;

    public void checkIfExists(Long cartId){
        if (!cartRepository.existsById(cartId)){
            throw new CartException("Cart with id " + cartId + " does not exists.");
        }
    }

    public void checkProductQuantity(int quantity){
        if (quantity < 1) {
            throw new CartException("Quantity can not be less than 1!");
        }
    }
}
