package com.shopping.cart.service.impl;

import com.shopping.cart.entity.Cart;
import com.shopping.cart.entity.CartItem;
import com.shopping.cart.repository.CartItemRepository;
import com.shopping.cart.repository.CartRepository;
import com.shopping.cart.repository.PersonRepository;
import com.shopping.cart.repository.ProductRepository;
import com.shopping.cart.service.CartService;
import com.shopping.cart.validator.CartValidator;
import com.shopping.cart.validator.PersonValidator;
import com.shopping.cart.validator.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private PersonValidator personValidator;

    @Autowired
    private ProductValidator productValidator;

    @Autowired
    private CartValidator cartValidator;

    @Override
    public Cart addCart(long personId) {
        return null;
    }

    @Override
    public List<Cart> getAllCarts(Long personId) {
        return (personId == null) ? cartRepository.findAll() : cartRepository.findAllByPersonId(personId);
    }

    @Transactional
    @Override
    public Cart addProductToCart(Long cartId, Long productId, Integer quantity) {
        cartValidator.checkIfExists(cartId);
        productValidator.checkIfExists(productId);
        cartValidator.checkProductQuantity(quantity);

        Cart cart = cartRepository.getById(cartId);
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(productRepository.getById(productId));
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
        return cart;
    }

    @Transactional
    @Override
    public Cart deleteProductFromCart(Long cartId, Long productId) {
        cartValidator.checkIfExists(cartId);
        productValidator.checkIfExists(productId);

        Cart cart = cartRepository.getById(cartId);
        List<CartItem> products = cart.getCartItems();
        for (CartItem productInCart : products) {
            if (productInCart.getProduct().getId().equals(productId)) {
                products.remove(productInCart);
                //cart.refreshTotalPrice();
                break;
            }
        }
        return cart;
    }

    @Override
    public String deleteById(long id) {
        return null;
    }
}
