package com.shopping.cart.controller;

import com.shopping.cart.entity.Product;
import com.shopping.cart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("add")
    public ResponseEntity<Product> add(@RequestBody Product product) {
        productService.createProduct(product);
        return ResponseEntity.ok(product);
    }

    @GetMapping("list")
    public ResponseEntity<List<Product>> list() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Product> update(@PathVariable long id, Double price) {
        return ResponseEntity.ok(productService.updatePrice(id, price));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        return ResponseEntity.ok(productService.delete(id));
    }
}
