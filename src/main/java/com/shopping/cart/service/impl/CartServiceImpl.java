package com.shopping.cart.service.impl;

import com.shopping.cart.dto.CartDTO;
import com.shopping.cart.entity.Cart;
import com.shopping.cart.entity.CartItem;
import com.shopping.cart.entity.Person;
import com.shopping.cart.entity.Product;
import com.shopping.cart.repository.CartRepository;
import com.shopping.cart.service.CartService;
import com.shopping.cart.service.PersonService;
import com.shopping.cart.service.ProductService;
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

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, PersonService personService, ProductService productService) {
        this.cartRepository = cartRepository;
        this.personService = personService;
        this.productService = productService;
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
                return null;
            }
        }

        CartItem newCartItem = new CartItem();
        newCartItem.setCart(cart);
        newCartItem.setProduct(product);
        newCartItem.setQuantity(quantity);
        cart.getCartItems().add(newCartItem);
        countTotalPrice(cart);

        return null;
    }

    private void countTotalPrice(Cart cart) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (CartItem cartItem : cart.getCartItems()) {
            totalPrice = totalPrice.add(cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
        }
        cart.setTotalPrice(totalPrice);
    }







    /*@Autowired
    private CartRepository cartRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

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
        //cartValidator.checkIfExists(cartId);
        //productValidator.checkIfExists(productId);
        //cartValidator.checkProductQuantity(quantity);

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
        //cartValidator.checkIfExists(cartId);
        //productValidator.checkIfExists(productId);

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
    public String deleteById(Long id) {
        return null;
    }*/
}
