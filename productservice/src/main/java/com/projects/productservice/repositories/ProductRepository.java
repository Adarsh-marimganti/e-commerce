package com.projects.productservice.repositories;

import com.projects.productservice.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Override
    Optional<Product> findById(Long id);

    @Override
    Page<Product> findAll(Pageable pageable);

    List<Product> findByTitle(String title);

    List<Product> findByTitleContains(String str);

    List<Product> findByTitleAndDescription(String title, String description);

    Optional<Product> findByImage(String url);

//    public Product replaceProduct(Long id, Product product);

    @Override
    Product save(Product product);

    @Override
    void delete(Product product);

    //HQL
    @Query("select p.title, p.description from Product p where p.id = :id")
    Product someRandomQuery(@Param("id") Long id);
}
