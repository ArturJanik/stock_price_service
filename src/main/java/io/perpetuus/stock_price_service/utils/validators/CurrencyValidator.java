package io.perpetuus.stock_price_service.utils.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import io.perpetuus.stock_price_service.config.ValidatorsConfig;
import io.perpetuus.stock_price_service.utils.constraints.ValidCurrency;

public class CurrencyValidator implements ConstraintValidator<ValidCurrency, String> {

    @Override
    public boolean isValid(String currency, ConstraintValidatorContext context) {
        if (currency == null) {
            return false;
        }

        return ValidatorsConfig.VALID_CURRENCIES.contains(currency);
    }

}
