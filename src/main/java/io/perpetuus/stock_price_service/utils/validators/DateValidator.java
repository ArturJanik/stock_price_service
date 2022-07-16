package io.perpetuus.stock_price_service.utils.validators;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import io.perpetuus.stock_price_service.utils.constraints.DateString;

public class DateValidator implements ConstraintValidator<DateString, String> {

    private String dateFormat;

    @Override
    public void initialize(final DateString constraintAnnotation) {
        this.dateFormat = constraintAnnotation.format();
    }
    
    @Override
    public boolean isValid(final String dateString, final ConstraintValidatorContext context) {
        if (dateString == null) {
            return false;
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(this.dateFormat);

        try {
            LocalDate.parse(dateString, dtf);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
