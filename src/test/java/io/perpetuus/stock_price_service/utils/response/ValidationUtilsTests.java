package io.perpetuus.stock_price_service.utils.response;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@SpringBootTest
public class ValidationUtilsTests {

    @Test
    public void shouldConvertBindingResultToErrorsList() {
        // given
        BindingResult br = Mockito.mock(BindingResult.class);

        List<FieldError> errors = new ArrayList<>();
        errors.add(createFieldError("stockRecord", "price"));
        errors.add(createFieldError("stockRecord", "ticker"));

        Mockito.when(br.hasErrors()).thenReturn(true);
        Mockito.when(br.getFieldErrors()).thenReturn(errors);

        // when
        var result = ValidationUtils.convertBindingResultToValidationErrors(br);
        var resultErrorForPrice = result.get(0);
        var resultErrorForTicker = result.get(1);

        // expect
        assertEquals(2, result.size());
        assertEquals("price", resultErrorForPrice.getField());
        assertEquals("ticker", resultErrorForTicker.getField());
        assertEquals("Our defined error message", resultErrorForPrice.getDefaultMessage());
        assertEquals("Our defined error message", resultErrorForTicker.getDefaultMessage());
    }
    
    private FieldError createFieldError(String objectName, String field) {
        return new FieldError(
            objectName,
            field,
            null,
            false,
            null,
            null, 
            "Our defined error message"
        );
    }
    
}
