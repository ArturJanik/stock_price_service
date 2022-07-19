package io.perpetuus.stock_price_service.utils.response;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import io.perpetuus.stock_price_service.models.StockRecord;

@SpringBootTest
public class ResponseEntityUtilTests {
    
    @Test
    void shouldCreateResponseEntityWithErrors() {
        // given
        var errors = new ArrayList<ValidationError>();
        var nameError = createValidationError("Name can't be empty", "name");
        var phoneError = createValidationError("Phone can't be empty", "phone");
        errors.add(nameError);
        errors.add(phoneError);

        // when
        var response = ResponseEntityUtils.createResponseEntityWithValidationErrors(errors);
        var statusCode = response.getStatusCode();
        var body = response.getBody().getResponse();
        var responseMessage = response.getBody().getResponseStatus().getMessage();

        // then
        var expectedStatusCode = HttpStatus.BAD_REQUEST;
        var expectedErrorsCount = 2;
        var expectedBodyResponse = errors;
        var expectedResponseMessage = "Validation error";

        assertEquals(expectedStatusCode, statusCode);
        assertEquals(expectedErrorsCount, body.size());
        assertEquals(expectedBodyResponse, body);
        assertEquals(expectedResponseMessage, responseMessage);
    }

    @Test
    void shouldCreateSuccessfulResponseEntity() {
        // given
        var message = "Fetch successfull";
        var httpStatusCode = HttpStatus.OK.value();
        var stock = new StockRecord("1", "AMD", "22-10-2022", new BigDecimal(26.05), "USD", "WSE");

        // when
        var response = ResponseEntityUtils.createSuccessfulResponseEntity(message, httpStatusCode, stock);
        var statusCode = response.getStatusCode();
        var body = response.getBody().getResponse();
        var responseMessage = response.getBody().getResponseStatus().getMessage();

        // then
        var expectedStatusCode = HttpStatus.OK;
        var expectedBodyResponse = stock;
        var expectedResponseMessage = message;

        assertEquals(expectedStatusCode, statusCode);
        assertEquals(expectedBodyResponse, body);
        assertEquals(expectedResponseMessage, responseMessage);
    }

    private ValidationError createValidationError(String message, String field) {
        var error = new ValidationError();

        error.setDefaultMessage(message);
        error.setField(field);
        
        return error;
    }
}
