package io.perpetuus.stock_price_service.utils.validators;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.validation.ConstraintValidatorContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CurrencyValidatorTests {

    CurrencyValidator validator;
    ConstraintValidatorContext context;

    @BeforeEach
    public void setup() throws Exception {
        validator = new CurrencyValidator();
        context = Mockito.mock(ConstraintValidatorContext.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"PLN", "USD", "EUR", "CHF", "CAD", "JPY", "GBP"})
    void shouldValidateAcceptedCurrency(String currency) {
        // when
        var result = validator.isValid(currency, context);

        // then
        assertEquals(true, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"zł", "$", "Euro", "CNY", "NOK"})
    void shouldValidateNotAcceptedCurrency(String currency) {
        // when
        var result = validator.isValid(currency, context);

        // then
        assertEquals(false, result);
    }
}
