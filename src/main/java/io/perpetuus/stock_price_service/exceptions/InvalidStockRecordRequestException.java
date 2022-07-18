package io.perpetuus.stock_price_service.exceptions;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.perpetuus.stock_price_service.utils.response.ValidationError;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidStockRecordRequestException extends RuntimeException {

    private List<ValidationError> errors;

    public InvalidStockRecordRequestException(List<ValidationError> errors) {
        super("Stock record validation failed!");
        this.errors = errors;
    }
    
    public List<ValidationError> getErrors() {
        return errors;
    }
    
}
