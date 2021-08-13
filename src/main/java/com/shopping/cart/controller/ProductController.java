package com.shopping.cart.controller;

import com.shopping.cart.dto.ProductPostDTO;
import com.shopping.cart.entity.Product;
import com.shopping.cart.request.UpdateProductRequest;
import com.shopping.cart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("shopping-cart/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/add")
    public String addPage(Model model) {
        model.addAttribute("productPostDto", new ProductPostDTO());
        return "addProduct";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute @Valid ProductPostDTO productPostDTO) {
        productService.createProduct(productPostDTO);
        return "redirect:/shopping-cart/product/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("products", productService.getProducts());
        return "productList";
    }

    @GetMapping("/update")
    public String updatePage(@RequestParam("id") Long id, Model model) {
        Product product = productService.getProduct(id);
        model.addAttribute("updateProductRequest", new UpdateProductRequest(id, product.getName(), product.getPrice()));
        return "updateProduct";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute @Valid UpdateProductRequest updateProductRequest) {
        productService.updateProduct(updateProductRequest.getId(), updateProductRequest);
        return "redirect:/shopping-cart/product/list";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        productService.delete(id);
        return "redirect:/shopping-cart/product/list";
    }
}