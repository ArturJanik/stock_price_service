package io.perpetuus.stock_price_service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.perpetuus.stock_price_service.models.StockRecord;
import io.perpetuus.stock_price_service.repositories.StockRecordRepository;

@Service
public class StockRecordServiceImpl implements StockRecordService {

    @Autowired
    private StockRecordRepository stockRecordRepository;

    @Override
    public List<StockRecord> findByTicker(String ticker) {
        return stockRecordRepository.findByTicker(ticker);
    }

    @Override
    public List<StockRecord> findByDate(String date) {
        return stockRecordRepository.findByDate(date);
    }

    @Override
    public StockRecord saveStockRecord(StockRecord record) {
        return stockRecordRepository.save(record);
    }
    
}
