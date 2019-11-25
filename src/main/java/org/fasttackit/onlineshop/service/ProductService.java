package org.fasttackit.onlineshop.service;

import org.fasttackit.onlineshop.domain.Product;
import org.fasttackit.onlineshop.exception.ResourceNotFoundException;
import org.fasttackit.onlineshop.persistance.ProductRepository;
import org.fasttackit.onlineshop.transfer.SaveProductRequest;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(ProductService.class);
    // IoC - inversion of control
    private final ProductRepository productRepository;

   // dependency injection
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(SaveProductRequest request){
        LOGGER.info("Creating product{}", request);
        Product product = new Product();
        product.setDescription(request.getDescription());
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setImageUrl(request.getImageUrl());

        return productRepository.save(product);

    }
    public Product getProduct(long id) {

        LOGGER.info("Retrieving product {}", id);

        // uusing optional
       return productRepository.findById(id)
               // lambda expresion
               .orElseThrow(()-> new ResourceNotFoundException("Product" + id + " does not exist"));

    }
}
