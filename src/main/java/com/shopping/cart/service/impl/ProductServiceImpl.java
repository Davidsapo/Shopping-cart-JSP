package com.shopping.cart.service.impl;

import com.shopping.cart.dto.ProductGetDTO;
import com.shopping.cart.dto.ProductPostDTO;
import com.shopping.cart.entity.Product;
import com.shopping.cart.exception.exceptions.NonUniqueValueException;
import com.shopping.cart.logger.AdvancedLogger;
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

    private static final AdvancedLogger log = new AdvancedLogger(ProductServiceImpl.class);

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
        if (productRepository.existsByNameIgnoreCase(productPostDTO.getName())) {
            RuntimeException exception = new NonUniqueValueException("Product", "name", productPostDTO.getName());
            log.warn("Fail to add product: " + productPostDTO, exception);
            throw exception;
        }
        Product newProduct = productRepository.save(mapper.productPostDTOToProduct(productPostDTO));
        log.info("New " + newProduct + " added.");
        return mapper.productToProductGetDTO(newProduct);
    }

    @Override
    public List<ProductGetDTO> getProducts() {
        log.info("Request to get product list.");
        return mapper.productsToProductGetDTOs(productRepository.findAll());
    }

    @Override
    public Product getProduct(Long id) {
        idValidator.validProductId(id);
        return productRepository.getById(id);
    }

    @Transactional
    @Override
    public ProductGetDTO updateProduct(Long id, UpdateProductRequest updateProductRequest) {
        Product productFromDB;
        String olgInfo;
        try {
            idValidator.validProductId(id);
            productFromDB = productRepository.getById(id);
            olgInfo = productFromDB.toString();
            String updatedName = updateProductRequest.getName();
            BigDecimal updatedPrice = updateProductRequest.getPrice();
            if (!productFromDB.getName().equalsIgnoreCase(updatedName) && productRepository.existsByNameIgnoreCase(updateProductRequest.getName())) {
                throw new NonUniqueValueException("Product", "name", updateProductRequest.getName());
            }
            if (Objects.nonNull(updatedName)) {
                productFromDB.setName(updatedName);
            }
            if (Objects.nonNull(updatedPrice)) {
                productFromDB.setPrice(updatedPrice);
            }
        } catch (Exception e) {
            log.warn("Fail to update product", e);
            throw e;
        }
        log.info(olgInfo + " updated to " + productFromDB);
        return mapper.productToProductGetDTO(productFromDB);
    }

    @Override
    public void delete(Long id) {
        try {
            idValidator.validProductId(id);
            Product product = productRepository.deleteByIdWithReturn(id);
            log.info("Product deleted. " + product);

        } catch (Exception e) {
            log.warn("Fail to delete product.", e);
            throw e;
        }
    }
}
