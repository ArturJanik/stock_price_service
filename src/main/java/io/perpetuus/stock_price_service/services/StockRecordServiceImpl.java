package io.perpetuus.stock_price_service.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import io.perpetuus.stock_price_service.models.StockRecord;

@Service
public class StockRecordServiceImpl implements StockRecordService {

    @Override
    public List<StockRecord> findByTicker(String ticker) {
        return dummyStocks();
    }

    @Override
    public List<StockRecord> findByDate(String date) {
        return dummyStocks();
    }

    @Override
    public StockRecord saveStockRecord(StockRecord record) {
        return dummyStocks().get(0);
    }

    private List<StockRecord> dummyStocks() {
        var stocks = new ArrayList<StockRecord>();
        var dummyStockRecord = new StockRecord("1", "PZU", "02-10-2022", BigDecimal.ONE, "PLN", "WSE");
        stocks.add(dummyStockRecord);
        return stocks;
    }
    
}
