package io.perpetuus.stock_price_service.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import io.perpetuus.stock_price_service.models.StockRecord;

public interface StockRecordRepository extends MongoRepository<StockRecord, String> {

    List<StockRecord> findByTicker(@Param("ticker") String ticker);
    
    List<StockRecord> findByDate(@Param("date") String date);

}
