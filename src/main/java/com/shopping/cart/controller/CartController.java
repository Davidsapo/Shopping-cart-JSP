package com.shopping.cart.controller;

import com.shopping.cart.dto.CartDTO;
import com.shopping.cart.request.AddToCartRequest;
import com.shopping.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add-product")
    public ResponseEntity<CartDTO> addProduct(@RequestBody @Valid AddToCartRequest request) {
        return ResponseEntity.ok(cartService.addProduct(request.getPersonID(), request.getProductID(), request.getQuantity()));
    }

    /*@PostMapping("add")
    public ResponseEntity<Cart> add(long personId) {
        return ResponseEntity.ok(cartService.addCart(personId));
    }

    @GetMapping("list")
    public ResponseEntity<List<Cart>> list(Long personId) {
        return ResponseEntity.ok(cartService.getAllCarts(personId));
    }

    @PutMapping("/{cartId}/add/product")
    public ResponseEntity<Cart> addProductToCart(@PathVariable Long cartId, Long productId, Integer quantity) {
        if (productId == null || quantity == null) {
            throw new CartException("Required parameters: productId, quantity");
        }
        return ResponseEntity.ok(cartService.addProductToCart(cartId, productId, quantity));
    }

    @PutMapping("/{cartId}/delete/product")
    public ResponseEntity<Cart> deleteProductFromCart(@PathVariable Long cartId, Long productId) {
        if (productId == null) {
            throw new CartException("Required parameters: productId");
        }
        return ResponseEntity.ok(cartService.deleteProductFromCart(cartId, productId));
    }

    @DeleteMapping("delete/{cartId}")
    public ResponseEntity<String> delete(@PathVariable long cartId) {
        return ResponseEntity.ok(cartService.deleteById(cartId));
    }*/
}
