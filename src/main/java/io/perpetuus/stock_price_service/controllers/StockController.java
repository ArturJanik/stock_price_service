package io.perpetuus.stock_price_service.controllers;

import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.perpetuus.stock_price_service.exceptions.InvalidStockRecordRequestException;
import io.perpetuus.stock_price_service.models.StockRecord;
import io.perpetuus.stock_price_service.services.StockRecordService;
import io.perpetuus.stock_price_service.utils.constraints.DateString;
import io.perpetuus.stock_price_service.utils.response.ParameterError;
import io.perpetuus.stock_price_service.utils.response.ResponseEntityUtils;
import io.perpetuus.stock_price_service.utils.response.RestResponse;
import io.perpetuus.stock_price_service.utils.response.ValidationError;
import io.perpetuus.stock_price_service.utils.response.ValidationUtils;

@Validated
@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockRecordService stockRecordService; 
    
    @Validated
    @GetMapping("/historical")
    public List<StockRecord> getHistoricalStockData(
        @RequestParam(required = false) @NotBlank @Size(min = 2) String stockTicker
    ) {
        return stockRecordService.findByTicker(stockTicker);
    }
    
    @Validated
    @GetMapping("/daily")
    public List<StockRecord> getDailyStockData(
        @RequestParam(required = false) @NotBlank @DateString String date
    ) {
        return stockRecordService.findByDate(date);
    }
    
    @PostMapping
    public ResponseEntity<RestResponse<StockRecord>> createStockRecord(
        @Validated @RequestBody StockRecord record,
        BindingResult result
    ) throws Exception {

        List<ValidationError> errors = ValidationUtils.convertBindingResultToValidationErrors(result);

        if (!errors.isEmpty()) {
            throw new InvalidStockRecordRequestException(errors);
        } else {
            var newRecord = stockRecordService.saveStockRecord(record);
        
            return ResponseEntityUtils.createSuccessfulResponseEntity(newRecord);
        }
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<RestResponse<List<ParameterError>>> handleConstraintViolationException(
        ConstraintViolationException ex
    ) {
        var errors = ValidationUtils.convertConstraintViolationsToParameterErrors(ex.getConstraintViolations());
        return ResponseEntityUtils.createResponseEntityWithParameterErrors(errors);
    }

    @ExceptionHandler(InvalidStockRecordRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<RestResponse<List<ValidationError>>> handleInvalidStockRecordRequestException(
        InvalidStockRecordRequestException ex
    ) {
        var errors = ex.getErrors();
        return ResponseEntityUtils.createResponseEntityWithValidationErrors(errors);
    }

}
