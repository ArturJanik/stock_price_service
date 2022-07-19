package io.perpetuus.stock_price_service.utils.response;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ResponseStatusTests {
 
    @Test
    void shouldContainStatusCode() {
        // given
        var statusCode = ResponseStatusCode.OK;

        // when
        var response = new ResponseStatus();
        response.setStatusCode(statusCode);

        // then
        assertEquals(statusCode, response.getStatusCode());
    }
 
    @Test
    void shouldContainMessage() {
        // given
        var message = "Response message";

        // when
        var response = new ResponseStatus();
        response.setMessage(message);

        // then
        assertEquals(message, response.getMessage());
    }

}