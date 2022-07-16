package io.perpetuus.stock_price_service.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.perpetuus.stock_price_service.models.StockRecord;
import io.perpetuus.stock_price_service.utils.constraints.DateString;
import io.perpetuus.stock_price_service.utils.response.RestResponse;

@Validated
@RestController
@RequestMapping("/stock")
public class StockController {
    
    @Validated
    @GetMapping("/historical")
    public List<StockRecord> getHistoricalStockData(
        @RequestParam(required = false) @NotBlank @Size(min = 2) String stockTicker
    ) {
        return dummyStock();
    }
    
    @Validated
    @GetMapping("/daily")
    public List<StockRecord> getDailyStockData(
        @RequestParam(required = false) @NotBlank @DateString String date
    ) {
        return dummyStock();
    }
    
    @PostMapping
    public String createStockRecord() {
        return "New stock #1";
    }

    private List<StockRecord> dummyStock() {
        var stocks = new ArrayList<StockRecord>();
        var dummyStockRecord = new StockRecord("1", "PZU", "02-10-2022", BigDecimal.ONE, "PLN", "WSE");
        stocks.add(dummyStockRecord);
        return stocks;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<RestResponse<List<String>>> handleConstraintViolationException(ConstraintViolationException ex) {
        var errors = ex.getConstraintViolations();
        var messages = new ArrayList<String>();
        for (ConstraintViolation<?> constraintViolation : errors) {
            messages.add(constraintViolation.getMessage());
        }
        RestResponse<List<String>> response = new RestResponse<List<String>>();
        response.setResponse(messages);

        return ResponseEntity
            .badRequest()
            .header("Content-Type", MediaType.APPLICATION_JSON.toString())
            .body(response);
    }

}
