package com.shopping.cart.controller;

import com.shopping.cart.entity.Product;
import com.shopping.cart.request.UpdateProductRequest;
import com.shopping.cart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<Product> add(@RequestBody @Valid Product product) {
        return ResponseEntity.ok(productService.createProduct(product));
    }

    @GetMapping("/list")
    public ResponseEntity<List<Product>> list() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> update(@PathVariable("id") Long id, @RequestBody @Valid UpdateProductRequest updateProductRequest) {
        return ResponseEntity.ok(productService.updatePrice(id, updateProductRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        productService.delete(id);
        return ResponseEntity.ok("Product deleted successfully.");
    }
}