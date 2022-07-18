package io.perpetuus.stock_price_service.utils.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.validation.BindingResult;

public class ValidationUtils {
    
    public static List<ValidationError> convertBindingResultToValidationErrors(BindingResult bindingResult) {
        List<ValidationError> errors = new ArrayList<ValidationError>();

        if (bindingResult != null && bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(fError -> {
                String message = fError.getDefaultMessage();
                String field = fError.getField();

                var error = new ValidationError();
                error.setDefaultMessage(message);
                error.setField(field);

                errors.add(error);
            });
        }

        return errors;
    }

    public static List<ParameterError> convertConstraintViolationsToParameterErrors(Set<ConstraintViolation<?>> violations) {
        List<ParameterError> errors = new ArrayList<ParameterError>();
        
        if(violations != null && !violations.isEmpty()) {
            for (ConstraintViolation<?> violation : violations) {
                String message = violation.getMessage();
                String property = violation.getPropertyPath().toString();
                String invalidValue = violation.getInvalidValue() != null ? violation.getInvalidValue().toString() : "";

                ParameterError error = new ParameterError();
                error.setDefaultMessage(message);
                error.setProperty(property);
                error.setInvalidValue(invalidValue);
                errors.add(error);
            }
        }

        return errors;
    }
    
}
