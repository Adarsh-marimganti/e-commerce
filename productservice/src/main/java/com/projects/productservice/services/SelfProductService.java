package com.projects.productservice.services;

import com.projects.productservice.exceptions.ProductNotFoundException;
import com.projects.productservice.models.Category;
import com.projects.productservice.models.Product;
import com.projects.productservice.repositories.CategoryRepository;
import com.projects.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
//@Primary
public class SelfProductService implements ProductService{
    private ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    SelfProductService(ProductRepository productRepository,
                       CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()) {
            throw new ProductNotFoundException(id, "Product not found");
        }
//        int a = 1/0;
        return productOptional.get();
    }

//    @Override
    public Page<Product> getAllProducts(int pageNumber, int pageSize) {
        return productRepository.findAll(PageRequest.of(pageNumber, pageSize,
                Sort.by("price").descending()  ));
    }

    @Override // PUT
    public Product replaceProduct(long id, Product product) {
//        return productRepository.replaceProduct(id, product);
        return null;
    }

    @Override // POST
    public Product createProduct(Product product) {
        // Before saving the product object, save the category object

        Category category = product.getCategory();
        if(category.getId() == null) {
            category = categoryRepository.save(category);
        }
        else {
            category = updateCategory(category);
        }
        product.setCategory(category);
        return productRepository.save(product);
    }
     // PATCH
    private Category updateCategory(Category category) {
        Optional<Category> category1 = categoryRepository.findById(category.getId());
        if(!category1.isPresent()) {
            return categoryRepository.save(category);
        }
        if(category.getDescription() != null) {
            category1.get().setDescription(category.getDescription());
        }
        category1.get().setUpdatedAt(new Date());
        return categoryRepository.save(category1.get());
    }

    @Override
    public void deleteProduct(Long id) throws ProductNotFoundException{
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()) {
            throw new ProductNotFoundException(id, " Product not found");
        }
        Product product = productOptional.get();
        productRepository.delete(product);
    }

}
