package io.perpetuus.stock_price_service.utils.response;

public interface RestResponse<T> {
    
    ResponseStatus getResponseStatus();
    T getResponse();
    int getHttpStatusCode();

}
