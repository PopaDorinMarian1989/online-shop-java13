package org.fasttackit.onlineshop.web;

import org.fasttackit.onlineshop.domain.Product;
import org.fasttackit.onlineshop.service.ProductService;
import org.fasttackit.onlineshop.transfer.GetPproductsRequest;
import org.fasttackit.onlineshop.transfer.ProductResponse;
import org.fasttackit.onlineshop.transfer.SaveProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
// todo: fix lazy loading for all endpoints
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody @Valid SaveProductRequest request) {
        Product product = productService.createProduct(request);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Product product = productService.getProduct(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getProducts(GetPproductsRequest request, Pageable pageable) {
        Page<ProductResponse> products = productService.getProducts(request, pageable);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody @Valid SaveProductRequest request){

    Product product = productService.updateProduct(id, request);
        return new ResponseEntity<>(product,HttpStatus.OK);

}

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {
         productService.deleteProduct(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
}