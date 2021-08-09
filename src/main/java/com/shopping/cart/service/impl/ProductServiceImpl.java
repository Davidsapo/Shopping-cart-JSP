package com.shopping.cart.service.impl;

import com.shopping.cart.dto.ProductGetDTO;
import com.shopping.cart.dto.ProductPostDTO;
import com.shopping.cart.entity.Product;
import com.shopping.cart.mapper.Mapper;
import com.shopping.cart.repository.ProductRepository;
import com.shopping.cart.request.UpdateProductRequest;
import com.shopping.cart.service.ProductService;
import com.shopping.cart.validator.IdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final IdValidator idValidator;

    private final Mapper mapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, IdValidator idValidator, Mapper mapper) {
        this.productRepository = productRepository;
        this.idValidator = idValidator;
        this.mapper = mapper;
    }

    @Override
    public ProductGetDTO createProduct(ProductPostDTO productPostDTO) {
        return mapper.productToProductGetDTO(productRepository.save(mapper.productPostDTOToProduct(productPostDTO)));
    }

    @Override
    public List<ProductGetDTO> getProducts() {
        return mapper.productsToProductGetDTOs(productRepository.findAll());
    }

    @Override
    public Product getProduct(Long id) {
        idValidator.validProductId(id);
        return productRepository.getById(id);
    }

    @Transactional
    @Override
    public ProductGetDTO updatePrice(Long id, UpdateProductRequest updateProductRequest) {
        idValidator.validProductId(id);
        Product productFromDB = productRepository.getById(id);
        String updatedName = updateProductRequest.getName();
        BigDecimal updatedPrice = updateProductRequest.getPrice();
        if (Objects.nonNull(updatedName)) {
            productFromDB.setName(updatedName);
        }
        if (Objects.nonNull(updatedPrice)) {
            productFromDB.setPrice(updatedPrice);
        }
        return mapper.productToProductGetDTO(productFromDB);
    }

    @Override
    public void delete(Long id) {
        idValidator.validProductId(id);
        productRepository.deleteById(id);
    }
}
