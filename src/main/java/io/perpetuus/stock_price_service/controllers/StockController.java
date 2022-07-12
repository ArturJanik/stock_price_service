package io.perpetuus.stock_price_service.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StockController {
    
    @GetMapping("/historical")
    public List<String> getHistoricalStockData(@RequestParam String stockTicker) {
        return dummyStock();
    }
    
    @GetMapping("/daily")
    public List<String> getDailyStockData(@RequestParam String date) {
        return dummyStock();
    }
    
    @PostMapping
    public String createStockRecord() {
        return "New stock #1";
    }

    private List<String> dummyStock() {
        var stocks = new ArrayList<String>();
        stocks.add("Stock #1");
        stocks.add("Stock #2");
        stocks.add("Stock #3");
        return stocks;
    }

}
