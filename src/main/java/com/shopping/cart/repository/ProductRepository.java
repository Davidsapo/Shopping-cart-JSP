package com.shopping.cart.repository;

import com.shopping.cart.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByNameIgnoreCase(String name);

    default Product deleteByIdWithReturn(Long id) {
        Product product = getById(id);
        deleteById(id);
        return product;
    }
}
