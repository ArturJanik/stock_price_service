package io.perpetuus.stock_price_service.services;

import java.util.List;

import io.perpetuus.stock_price_service.models.StockRecord;

public interface StockRecordService {
    
    List<StockRecord> findByTicker(String ticker);

    List<StockRecord> findByDate(String date);

    StockRecord saveStockRecord(StockRecord record);

}
