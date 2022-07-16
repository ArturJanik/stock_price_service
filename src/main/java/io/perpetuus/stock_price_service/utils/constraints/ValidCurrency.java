package io.perpetuus.stock_price_service.utils.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import io.perpetuus.stock_price_service.config.ValidatorsConfig;
import io.perpetuus.stock_price_service.utils.validators.CurrencyValidator;

@Constraint(validatedBy=CurrencyValidator.class)
@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidCurrency {
    String message() default "Currency must be one of accepted: " + ValidatorsConfig.VALID_CURRENCIES_STRING;
    Class <?> [] groups() default {};
    Class <? extends Payload> [] payload() default {};
}
