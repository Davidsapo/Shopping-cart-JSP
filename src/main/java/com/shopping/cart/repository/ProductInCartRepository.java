package com.shopping.cart.repository;

import com.shopping.cart.entity.ProductInCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductInCartRepository extends JpaRepository<ProductInCart, Long> {
}
