package com.example.day1.product;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = ProductControllerAdvice.class)
public class ProductControllerAdvice {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorMessageResponse> productNotFound(){
        ErrorMessageResponse  errorMessageResponse = new ErrorMessageResponse("Product not found in system");
        return new ResponseEntity<>(errorMessageResponse, HttpStatus.NOT_FOUND);
    }

}
