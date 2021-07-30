package com.shopping.cart.controller;

import com.shopping.cart.dto.CartDTO;
import com.shopping.cart.entity.Cart;
import com.shopping.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("add")
    public ResponseEntity<CartDTO> add(long personId) {
        return ResponseEntity.ok(new CartDTO(cartService.addCart(personId)));
    }

    @GetMapping("list")
    public ResponseEntity<List<CartDTO>> list(Long personId) {
        List<CartDTO> cartList = new ArrayList<>();
        for (Cart cart : cartService.getAllCarts(personId)) {
            cartList.add(new CartDTO(cart));
        }
        return ResponseEntity.ok(cartList);
    }

    @PutMapping("/{cartId}/add/product")
    public ResponseEntity<CartDTO> addProductToCart(@PathVariable Long cartId, Long productId, Integer quantity) {
        return ResponseEntity.ok(new CartDTO(cartService.addProductToCart(cartId, productId, quantity)));
    }

    @PutMapping("/{cartId}/delete/product")
    public ResponseEntity<CartDTO> deleteProductFromCart(@PathVariable Long cartId, Long productId) {
        return ResponseEntity.ok(new CartDTO(cartService.deleteProductFromCart(cartId, productId)));
    }

    @DeleteMapping("delete/{cartId}")
    public ResponseEntity<String> delete(@PathVariable long cartId) {
        return ResponseEntity.ok(cartService.deleteById(cartId));
    }
}
