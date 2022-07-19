package io.perpetuus.stock_price_service.utils.response;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class ResponseEntityUtils {
    
    public static ResponseEntity<RestResponse<List<ParameterError>>> createResponseEntityWithParameterErrors(
        List<ParameterError> errors
    ) {
        var response = new RestResponseImpl<List<ParameterError>>();
        response.setResponse(errors);

        return ResponseEntity
            .badRequest()
            .header("Content-Type", MediaType.APPLICATION_JSON.toString())
            .body(response);
    }

    public static ResponseEntity<RestResponse<List<ValidationError>>> createResponseEntityWithValidationErrors(
        List<ValidationError> errors
    ) {
        var response = new RestResponseImpl<List<ValidationError>>();
        response.setResponse(errors);

        return ResponseEntity
            .badRequest()
            .header("Content-Type", MediaType.APPLICATION_JSON.toString())
            .body(response);
    }

    public static <T> ResponseEntity<RestResponse<T>> createSuccessfulResponseEntity(
        T response
    ) {
        var fullResponse = new RestResponseImpl<T>();
        fullResponse.setResponse(response);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .header("Content-Type", MediaType.APPLICATION_JSON.toString())
            .body(fullResponse);
    }

}
