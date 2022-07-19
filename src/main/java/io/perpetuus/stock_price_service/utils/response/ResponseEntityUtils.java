package io.perpetuus.stock_price_service.utils.response;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import io.perpetuus.stock_price_service.models.StockRecord;

public class ResponseEntityUtils {
    
    public static ResponseEntity<RestResponse<List<ParameterError>>> createResponseEntityWithParameterErrors(
        List<ParameterError> errors
    ) {
        var response = new RestResponse<List<ParameterError>>();
        response.setResponse(errors);

        return ResponseEntity
            .badRequest()
            .header("Content-Type", MediaType.APPLICATION_JSON.toString())
            .body(response);
    }

    public static ResponseEntity<RestResponse<List<ValidationError>>> createResponseEntityWithValidationErrors(
        List<ValidationError> errors
    ) {
        var response = new RestResponse<List<ValidationError>>();
        response.setResponse(errors);

        return ResponseEntity
            .badRequest()
            .header("Content-Type", MediaType.APPLICATION_JSON.toString())
            .body(response);
    }

    public static ResponseEntity<RestResponse<StockRecord>> createSuccessfulResponseEntity(
        StockRecord newRecord
    ) {
        var response = new RestResponse<StockRecord>();
        response.setResponse(newRecord);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .header("Content-Type", MediaType.APPLICATION_JSON.toString())
            .body(response);
    }

}
