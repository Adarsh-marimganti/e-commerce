package com.projects.productservice.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductNotFoundException extends Exception{
    Long id;
    String message;
    public ProductNotFoundException(Long id, String message) {
        super(message);
        this.id = id;
    }
}
