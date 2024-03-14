package br.com.stoom.store.exception;

import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<ProductException> handleProductException(ProductException ex) {
        ProductException errorResponse = new ProductException(ex.getCode(), ex.getMessage());
        return new ResponseEntity(errorResponse.toString(), HttpStatus.valueOf(ex.getCode()));
    }
    @ExceptionHandler(PropertyValueException.class)
    public ResponseEntity<ProductException> handleProductException(PropertyValueException ex) {
        ProductException errorResponse = new ProductException(HttpStatus.BAD_REQUEST.value(), ex.getPropertyName());
        return new ResponseEntity(errorResponse.toString(), HttpStatus.valueOf(HttpStatus.BAD_REQUEST.value()));
    }
}