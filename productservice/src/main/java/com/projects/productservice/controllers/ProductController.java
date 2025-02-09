package com.projects.productservice.controllers;

import com.projects.productservice.commons.AuthCommons;
import com.projects.productservice.dtos.UserDto;
import com.projects.productservice.exceptions.ProductNotFoundException;
import com.projects.productservice.models.Product;
import com.projects.productservice.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    
    private final ProductService productService;
    private final AuthCommons authCommons;

    public ProductController(ProductService productService, AuthCommons authCommons) {
        this.productService = productService;
        this.authCommons = authCommons;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> fetchProductById(@PathVariable("id") long id) throws ProductNotFoundException {
        Product product = productService.getProductById(id);
        return (product != null) 
                ? ResponseEntity.ok(product) 
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @GetMapping
    public ResponseEntity<Page<Product>> retrieveAllProducts(@RequestParam("pageNumber") int pageNumber,
                                                             @RequestParam("pageSize") int pageSize) {
        Page<Product> products = productService.getAllProducts(pageNumber, pageSize);
        return ResponseEntity.ok(products);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
        Product updatedProduct = productService.replaceProduct(id, product);
        return ResponseEntity.ok(updatedProduct);
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeProduct(@PathVariable("id") Long id) throws ProductNotFoundException {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
