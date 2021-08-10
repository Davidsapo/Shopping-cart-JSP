package com.shopping.cart.service.impl;

import com.shopping.cart.dto.CartDTO;
import com.shopping.cart.entity.Cart;
import com.shopping.cart.entity.CartItem;
import com.shopping.cart.entity.Person;
import com.shopping.cart.entity.Product;
import com.shopping.cart.mapper.Mapper;
import com.shopping.cart.repository.CartRepository;
import com.shopping.cart.service.CartService;
import com.shopping.cart.service.PersonService;
import com.shopping.cart.service.ProductService;
import com.shopping.cart.validator.IdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final PersonService personService;
    private final ProductService productService;
    private final Mapper mapper;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, PersonService personService, ProductService productService, Mapper mapper) {
        this.cartRepository = cartRepository;
        this.personService = personService;
        this.productService = productService;
        this.mapper = mapper;
    }

    @Override
    public CartDTO fetchCart(Long personId) {
        return mapper.cartToCartDTO(personService.getPerson(personId).getCart());
    }

    @Override
    @Transactional
    public CartDTO addProduct(Long personID, Long productID, Integer quantity) {
        Person person = personService.getPerson(personID);
        Cart cart = person.getCart();
        Product product = productService.getProduct(productID);

        if (cart.getCartItems() != null && !cart.getCartItems().isEmpty()) {
            Optional<CartItem> cartItem = cart.getCartItems().stream().filter(cI -> Objects.equals(cI.getProduct().getId(), productID)).findFirst();
            if (cartItem.isPresent()) {
                cartItem.get().setQuantity(cartItem.get().getQuantity() + quantity);
                countTotalPrice(cart);
                return mapper.cartToCartDTO(cart);
            }
        }

        CartItem newCartItem = new CartItem();
        newCartItem.setCart(cart);
        newCartItem.setProduct(product);
        newCartItem.setQuantity(quantity);
        cart.getCartItems().add(newCartItem);
        countTotalPrice(cart);
        return mapper.cartToCartDTO(cart);
    }

    private void countTotalPrice(Cart cart) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (CartItem cartItem : cart.getCartItems()) {
            totalPrice = totalPrice.add(cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
        }
        cart.setTotalPrice(totalPrice);
    }

}
