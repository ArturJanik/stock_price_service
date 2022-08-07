package io.perpetuus.stock_price_service.utils.response;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseEntityUtils {

    private static <T> ResponseEntity<RestResponse<T>> createResponseEntity(RestResponse<T> response) {
        return ResponseEntity
            .status(HttpStatus.valueOf(response.getHttpStatusCode()))
            .body(response);
    }

    private static ResponseStatus getErrorResponseStatus(String message) {
        ResponseStatus status = new ResponseStatus();
        status.setMessage(message);
        status.setStatusCode(ResponseStatusCode.ERROR);

        return status;
    }

    private static ResponseStatus getSuccessResponseStatus(String message) {
        ResponseStatus status = new ResponseStatus();
        status.setMessage(message);
        status.setStatusCode(ResponseStatusCode.OK);

        return status;
    }
    
    public static ResponseEntity<RestResponse<List<ParameterError>>> createResponseEntityWithParameterErrors(
        List<ParameterError> errors
    ) {
        var response = new RestResponseImpl<List<ParameterError>>();

        if (errors != null && !errors.isEmpty()) {
            ResponseStatus responseStatus = getErrorResponseStatus("Parameter error");

            response.setResponse(errors);
            response.setResponseStatus(responseStatus);
            response.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
        }

        return createResponseEntity(response);
    }

    public static ResponseEntity<RestResponse<List<ValidationError>>> createResponseEntityWithValidationErrors(
        List<ValidationError> errors
    ) {
        var response = new RestResponseImpl<List<ValidationError>>();

        if (errors != null && !errors.isEmpty()) {
            ResponseStatus responseStatus = getErrorResponseStatus("Validation error");

            response.setResponse(errors);
            response.setResponseStatus(responseStatus);
            response.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
        }

        return createResponseEntity(response);
    }

    public static ResponseEntity<RestResponse<String>> createResponseEntityWithDataAccessError(
        String error
    ) {
        var response = new RestResponseImpl<String>();
        ResponseStatus responseStatus = getErrorResponseStatus("Data access error");

        response.setResponse(error);
        response.setResponseStatus(responseStatus);
        response.setHttpStatusCode(HttpStatus.SERVICE_UNAVAILABLE.value());

        return createResponseEntity(response);
    }

    public static <T> ResponseEntity<RestResponse<T>> createSuccessfulResponseEntity(
        String message,
        int httpStatusCode,
        T response
    ) {
        var fullResponse = new RestResponseImpl<T>();

        ResponseStatus responseStatus = getSuccessResponseStatus(message);
        fullResponse.setResponseStatus(responseStatus);
        fullResponse.setHttpStatusCode(httpStatusCode);
        fullResponse.setResponse(response);

        return createResponseEntity(fullResponse);
    }

    public static <T> ResponseEntity<RestResponse<T>> createSuccessfulGetResponse(T data) {
        return createSuccessfulResponseEntity(
            "Fetch successfull",
            HttpStatus.OK.value(),
            data
        );
    }

}
