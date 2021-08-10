package com.shopping.cart.controller;

import com.shopping.cart.dto.CartDTO;
import com.shopping.cart.request.AddToCartRequest;
import com.shopping.cart.request.UpdateCartItemRequest;
import com.shopping.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<CartDTO> fetchCart(@RequestParam Long personId) {
        return ResponseEntity.ok(cartService.fetchCart(personId));
    }

    @PostMapping("/add-product")
    public ResponseEntity<CartDTO> addProduct(@RequestBody @Valid AddToCartRequest request) {
        return ResponseEntity.ok(cartService.addProduct(request.getPersonID(), request.getProductID(), request.getQuantity()));
    }

    @PutMapping("/update")
    public ResponseEntity<CartDTO> updateCartItem(@RequestBody @Valid UpdateCartItemRequest request) {
        return ResponseEntity.ok(cartService.updateCartItem(request.getPersonId(), request.getCartItemId(), request.getQuantity()));
    }

}
