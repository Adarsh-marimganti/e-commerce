package com.projects.productservice.controllers;

import com.projects.productservice.commons.AuthCommons;
import com.projects.productservice.dtos.UserDto;
import com.projects.productservice.exceptions.ProductNotFoundException;
import com.projects.productservice.models.Product;
import com.projects.productservice.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {
    @Autowired
    private ProductController productController;

    @MockBean // This is a mocked object
    private ProductService productService;

    @MockBean
    private AuthCommons authCommons;

    @Test
    void testValidGetProductById() throws ProductNotFoundException {
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Macbook pro");
        product.setPrice(150000);
        when(productService.getProductById(1L))
                .thenReturn(product);

        UserDto userDto = new UserDto();
        userDto.setName("Naveen");
        userDto.setEmail("naveen@gmail.com");
        when(authCommons.validateToken("4F03IQPH4NlasWIbN69s5unofTS01LLzMEin7UA5czJsKy64W5tq0B4vIsmoGTBFCnG9nls6KLaNqdG2h2aBV2qIOnEjP7g3aL5YgYP5GMTFVi1d4C0VcwrHThGyWVPL"))
                .thenReturn(userDto);

        ResponseEntity<Product> responseEntity = productController.getProductById(1L);

        Product actualProduct = responseEntity.getBody();
        assertEquals(product, actualProduct);
//        assertThrows(ProductNotFoundException.class, null);
    }
    @Test
    void testValidGetAllProducts() {
//        List<Product> expectedProducts = List.of(new Product(), new Product(), new Product());
//        when(productService.getAllProducts())
//                .thenReturn(expectedProducts);
//
//        List<Product> actualProducts = productController.getAllProducts();
//        assertIterableEquals(expectedProducts, actualProducts);
    }

    @Test
    void testInvalidGetProductById() throws ProductNotFoundException {
        when(productService.getProductById(100L))
                .thenThrow(new ProductNotFoundException(100L, "no product with given id found"));

        assertThrows(ProductNotFoundException.class, () -> productController.getProductById(100L));
    }
}
