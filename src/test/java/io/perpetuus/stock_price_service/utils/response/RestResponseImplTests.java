package io.perpetuus.stock_price_service.utils.response;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

@SpringBootTest
public class RestResponseImplTests {
 
    @Test
    void shouldContainResponseStatus() {
        // given
        var responseStatus = new ResponseStatus();

        // when
        var response = new RestResponseImpl<>();
        response.setResponseStatus(responseStatus);

        // then
        assertEquals(responseStatus, response.getResponseStatus());
    }
 
    @Test
    void shouldContainResponse() {
        // given
        var responseData = "Response data";

        // when
        var response = new RestResponseImpl<>();
        response.setResponse(responseData);

        // then
        assertEquals(responseData, response.getResponse());
    }
 
    @Test
    void shouldContainStatusCode() {
        // given
        var code = HttpStatus.ACCEPTED.value();

        // when
        var response = new RestResponseImpl<>();
        response.setHttpStatusCode(code);

        // then
        assertEquals(code, response.getHttpStatusCode());
    }

}
