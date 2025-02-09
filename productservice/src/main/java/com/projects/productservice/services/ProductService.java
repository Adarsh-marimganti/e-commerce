package com.projects.productservice.services;

import com.projects.productservice.exceptions.ProductNotFoundException;
import com.projects.productservice.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    public Product getProductById(Long id) throws ProductNotFoundException;
    public Page<Product> getAllProducts(int pageNumber, int pageSize);
    public Product replaceProduct(long id, Product product);
    public Product createProduct(Product product);
    public void deleteProduct(Long id) throws ProductNotFoundException;
}
