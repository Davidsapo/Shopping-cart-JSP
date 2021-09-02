package com.shopping.cart.service.impl;

import com.shopping.cart.dto.CartDTO;
import com.shopping.cart.dto.CartItemDTO;
import com.shopping.cart.entity.Cart;
import com.shopping.cart.entity.CartItem;
import com.shopping.cart.entity.Person;
import com.shopping.cart.entity.Product;
import com.shopping.cart.logger.AdvancedLogger;
import com.shopping.cart.mapper.Mapper;
import com.shopping.cart.repository.CartItemRepository;
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

    private static final AdvancedLogger log = new AdvancedLogger(CartServiceImpl.class);

    private final CartItemRepository cartItemRepository;
    private final PersonService personService;
    private final ProductService productService;
    private final Mapper mapper;

    @Autowired
    public CartServiceImpl(CartItemRepository cartItemRepository, PersonService personService,
                           ProductService productService, Mapper mapper) {
        this.cartItemRepository = cartItemRepository;
        this.personService = personService;
        this.productService = productService;
        this.mapper = mapper;
    }

    @Override
    public CartDTO fetchCart() {
        log.info("Request to fetch cart.");
        return mapper.cartToCartDTO(personService.getAuthorizedPerson().getCart());
    }

    @Override
    @Transactional
    public CartDTO addProduct(Long productID, Integer quantity) {
        Cart cart;
        Product product;
        try {
            Person person = personService.getAuthorizedPerson();
            cart = person.getCart();
            product = productService.getProduct(productID);

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
            log.info(product + " added to cart.");
            countTotalPrice(cart);
        } catch (Exception e) {
            log.warn("Adding product to cart fails.", e);
            throw e;
        }
        return mapper.cartToCartDTO(cart);
    }

    @Override
    @Transactional
    public CartDTO updateCart(CartDTO cartDTO) {
        Cart currentCart;
        try {
            currentCart = personService.getAuthorizedPerson().getCart();
            List<CartItemDTO> newCartItems = cartDTO.getCartItems();
            List<CartItem> oldCartItems = currentCart.getCartItems();
            int quantity;
            for (int i = 0; i < newCartItems.size(); i++) {
                quantity = newCartItems.get(i).getQuantity();
                if (quantity > 0) {
                    oldCartItems.get(i).setQuantity(quantity);
                }
            }
            log.info("Updated cart: " + mapper.cartToCartDTO(currentCart));
            countTotalPrice(currentCart);
        } catch (Exception e) {
            log.warn("Cart updating fails", e);
            throw e;
        }
        return mapper.cartToCartDTO(currentCart);
    }

    @Override
    @Transactional
    public CartDTO deleteCartItem(Long cartItemId) {
        Cart cart = personService.getAuthorizedPerson().getCart();
        CartItem deletedCI = cartItemRepository.getById(cartItemId);
        if (!cart.getCartItems().removeIf(item -> Objects.equals(item.getId(), cartItemId))) {
            RuntimeException e = new NoSuchElementException("No cart item with id: " + cartItemId);
            log.warn("Cart item deleting fails.", e);
            throw e;
        }
        log.info("Item: " + deletedCI + " deleted from cart.");
        countTotalPrice(cart);
        return mapper.cartToCartDTO(cart);
    }

    @Override
    @Transactional
    public void emptyCart() {
        Cart cart = personService.getAuthorizedPerson().getCart();
        cart.getCartItems().clear();
        cart.setTotalPrice(BigDecimal.ZERO);
        log.info("Cart is empty.");
    }

    private void countTotalPrice(Cart cart) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (CartItem cartItem : cart.getCartItems()) {
            totalPrice = totalPrice.add(cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
        }
        cart.setTotalPrice(totalPrice);
        log.info("Cart total price: " + totalPrice);
    }

}
