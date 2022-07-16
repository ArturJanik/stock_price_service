package io.perpetuus.stock_price_service.utils.response;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class RestResponse<T> {
    
    private T response;

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
