package com.shopping.cart.controller;

import com.shopping.cart.dto.CartDTO;
import com.shopping.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("shopping-cart/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/")
    public String cartPage(Model model) {
        model.addAttribute("cart", cartService.fetchCart());
        return "cart";
    }

    @GetMapping("/add-product")
    public String addProduct(@RequestParam Long productId) {
        cartService.addProduct(productId, 1);
        return "redirect:/shopping-cart/cart/";
    }

    @PostMapping("/update-cart")
    public String updateCart(@ModelAttribute CartDTO cartDTO) {
        cartService.updateCart(cartDTO);
        return "redirect:/shopping-cart/cart/";
    }

    @GetMapping("/delete-cart-item")
    public String deleteCartItem(@RequestParam Long cartItemId) {
        cartService.deleteCartItem(cartItemId);
        return "redirect:/shopping-cart/cart/";
    }

    @GetMapping("/empty")
    public String emptyCart() {
        cartService.emptyCart();
        return "redirect:/shopping-cart/cart/";
    }

    @GetMapping("/order")
    public String order(Model model) {
        model.addAttribute("cart", cartService.fetchCart());
        cartService.emptyCart();
        return "order";
    }

}
