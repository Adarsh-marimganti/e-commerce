package com.projects.productservice.exceptionhandlers;

import com.projects.productservice.dtos.ExceptionDto;
import com.projects.productservice.dtos.ProductNotFoundExceptionDto;
import com.projects.productservice.exceptions.ProductNotFoundException;
import com.projects.productservice.models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ExceptionDto> handleArithmeticException() {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setErrorMessage("Arithmetic Exception found");
        exceptionDto.setResolution("Nothing can be done");
        return new ResponseEntity<>(exceptionDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<ExceptionDto> handleArrayIndexOutOfBoundException() {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setErrorMessage("You are trying to access the out of bounds index");
        exceptionDto.setResolution("Debug and find the error line");
        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ProductNotFoundExceptionDto> productNotFoundException(ProductNotFoundException exception) {
        ProductNotFoundExceptionDto dto = new ProductNotFoundExceptionDto();
        dto.setMessage("Product with id " + exception.getId() + " not found...");
        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }

//    public ResponseEntity<Product> findSomething
}
