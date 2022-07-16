package io.perpetuus.stock_price_service.utils.validators;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.validation.ConstraintValidatorContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import io.perpetuus.stock_price_service.config.ValidatorsConfig;
import io.perpetuus.stock_price_service.utils.constraints.DateString;

@SpringBootTest
public class DateValidatorTests {

    DateValidator validator;

    @BeforeEach
    public void setup() throws Exception {
        validator = new DateValidator();
        var constraintAnnotation = Mockito.mock(DateString.class);
        Mockito.when(constraintAnnotation.format()).thenReturn(ValidatorsConfig.VALID_DATE_FORMAT);        
        validator.initialize(constraintAnnotation);
    }

    @Test
    void shouldValidateCorrectDateString() {
        // given
        var dateString = "10-12-2022";

        // when
        var context = Mockito.mock(ConstraintValidatorContext.class);
        var result = validator.isValid(dateString, context);

        // then
        assertEquals(true, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"10-23-2022", "10-Jan-2022", "10/01/2022", "3-20-2022", "2022-10-30"})
    void shouldValidateIncorrectDateString(String dateString) {
        // when
        var context = Mockito.mock(ConstraintValidatorContext.class);
        var result = validator.isValid(dateString, context);

        // then
        assertEquals(false, result);
    }

}
