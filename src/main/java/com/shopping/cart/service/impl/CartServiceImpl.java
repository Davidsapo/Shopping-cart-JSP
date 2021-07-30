package com.shopping.cart.service.impl;

import com.shopping.cart.entity.Cart;
import com.shopping.cart.entity.ProductInCart;
import com.shopping.cart.exceptions.CartException;
import com.shopping.cart.repository.CartRepository;
import com.shopping.cart.repository.PersonRepository;
import com.shopping.cart.repository.ProductRepository;
import com.shopping.cart.service.CartService;
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

    @Override
    public Cart addCart(long personId) {
        if (!personRepository.existsById(personId)) {
            throw new CartException("Person with id " + personId + " does not exists.");
        }
        Cart newCart = new Cart(personRepository.getById(personId));
        cartRepository.save(newCart);
        return newCart;
    }

    @Override
    public List<Cart> getAllCarts(Long personId) {
        return (personId == null) ? cartRepository.findAll() : cartRepository.findAllByPersonId(personId);
    }

    @Transactional
    @Override
    public Cart addProductToCart(Long cartId, Long productId, Integer quantity) {
        if (!cartRepository.existsById(cartId)) {
            throw new CartException("Cart with id " + cartId + " does not exists.");
        }
        if (productId == null || quantity == null) {
            throw new CartException("Required parameters: productId, quantity");
        }
        if (!productRepository.existsById(productId)) {
            throw new CartException("Product with id " + productId + " does not exists.");
        }
        if (quantity < 1) {
            throw new CartException("Quantity can not be less than 1!");
        }
        Cart cart = cartRepository.getById(cartId);
        cart.addProduct(new ProductInCart(productRepository.getById(productId), quantity));
        return cart;
    }

    @Transactional
    @Override
    public Cart deleteProductFromCart(Long cartId, Long productId) {
        if (!cartRepository.existsById(cartId)) {
            throw new CartException("Cart with id " + cartId + " does not exists.");
        }
        if (productId == null) {
            throw new CartException("Required parameters: productId");
        }
        if (!productRepository.existsById(productId)) {
            throw new CartException("Product with id " + productId + " does not exists.");
        }
        Cart cart = cartRepository.getById(cartId);
        cart.removeProduct(productId);
        return cart;
    }

    @Override
    public String deleteById(long id) {
        if (!cartRepository.existsById(id)) {
            return "No cart with id " + id;
        }
        cartRepository.deleteById(id);
        return "Deleted";
    }
}
