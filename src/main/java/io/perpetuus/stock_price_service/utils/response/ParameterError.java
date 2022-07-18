package io.perpetuus.stock_price_service.utils.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(Include.NON_NULL)
public class ParameterError {
    
    private String property;
    private String code;
    private String defaultMessage;
    private String invalidValue;
    
    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }
    
    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }
    
    public String getInvalidValue() {
        return invalidValue;
    }
    
    public void setInvalidValue(String invalidValue) {
        this.invalidValue = invalidValue;
    }
    
}
