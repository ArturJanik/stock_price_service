package io.perpetuus.stock_price_service.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.perpetuus.stock_price_service.models.StockRecord;

@RestController
@RequestMapping("/stock")
public class StockController {
    
    @GetMapping("/historical")
    public List<StockRecord> getHistoricalStockData(@RequestParam String stockTicker) {
        return dummyStock();
    }
    
    @GetMapping("/daily")
    public List<StockRecord> getDailyStockData(@RequestParam String date) {
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

}
