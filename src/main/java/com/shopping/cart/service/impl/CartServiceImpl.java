package com.shopping.cart.service.impl;

import com.shopping.cart.dto.CartDTO;
import com.shopping.cart.dto.CartItemDTO;
import com.shopping.cart.entity.Cart;
import com.shopping.cart.entity.CartItem;
import com.shopping.cart.entity.Person;
import com.shopping.cart.entity.Product;
import com.shopping.cart.mapper.Mapper;
import com.shopping.cart.service.CartService;
import com.shopping.cart.service.PersonService;
import com.shopping.cart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final PersonService personService;
    private final ProductService productService;
    private final Mapper mapper;

    @Autowired
    public CartServiceImpl(PersonService personService, ProductService productService, Mapper mapper) {
        this.personService = personService;
        this.productService = productService;
        this.mapper = mapper;
    }

    @Override
    public CartDTO fetchCart() {
        return mapper.cartToCartDTO(personService.getAuthorizedPerson().getCart());
    }

    @Override
    @Transactional
    public CartDTO addProduct(Long productID, Integer quantity) {
        Person person = personService.getAuthorizedPerson();
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

    @Override
    @Transactional
    public CartDTO updateCart(CartDTO cartDTO) {
        Cart currentCart = personService.getAuthorizedPerson().getCart();
        List<CartItemDTO> newCartItems = cartDTO.getCartItems();
        List<CartItem> oldCartItems = currentCart.getCartItems();
        int quantity;
        for (int i = 0; i < newCartItems.size(); i++) {
            quantity = newCartItems.get(i).getQuantity();
            if (quantity > 0) {
                oldCartItems.get(i).setQuantity(quantity);
            }
        }
        countTotalPrice(currentCart);
        return mapper.cartToCartDTO(currentCart);
    }

    @Override
    @Transactional
    public CartDTO updateCartItem(Long cartItemId, Integer quantity) {
        Cart cart = personService.getAuthorizedPerson().getCart();
        CartItem cartItem = cart.getCartItems()
                .stream()
                .filter(item -> Objects.equals(item.getId(), cartItemId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No cart item with id: " + cartItemId));
        cartItem.setQuantity(quantity);
        countTotalPrice(cart);
        return mapper.cartToCartDTO(cart);
    }

    @Override
    @Transactional
    public CartDTO deleteCartItem(Long cartItemId) {
        Cart cart = personService.getAuthorizedPerson().getCart();
        if (!cart.getCartItems().removeIf(item -> Objects.equals(item.getId(), cartItemId))) {
            throw new NoSuchElementException("No cart item with id: " + cartItemId);
        }
        countTotalPrice(cart);
        return mapper.cartToCartDTO(cart);
    }

    @Override
    @Transactional
    public void emptyCart() {
        Cart cart = personService.getAuthorizedPerson().getCart();
        cart.getCartItems().clear();
        cart.setTotalPrice(BigDecimal.ZERO);
    }

    private void countTotalPrice(Cart cart) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (CartItem cartItem : cart.getCartItems()) {
            totalPrice = totalPrice.add(cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
        }
        cart.setTotalPrice(totalPrice);
    }

}
