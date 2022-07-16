package io.perpetuus.stock_price_service.models;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.perpetuus.stock_price_service.utils.constraints.DateString;
import io.perpetuus.stock_price_service.utils.constraints.ValidCurrency;

@Document(collection = "stockRecords")
public class StockRecord {

    @Id
    private final String id;

    @NotBlank(message = "Ticker is required")
	@Size(min=2, max=10, message = "Ticker must have between 2 and 10 characters")
    private String ticker;

    @NotBlank(message = "Date is required")
    @DateString
    private String date;

    @NotNull(message = "Price is required")
    @Positive(message = "Price cannot be negative or zero")
    private BigDecimal price;

    @NotNull(message = "Currency is required")
    @ValidCurrency
    private String currency;

    @NotBlank(message = "Stock exchange is required")
    private String market;

    public StockRecord(String id, String ticker, String date, BigDecimal price, String currency, String market) {
        this.id = id;
        this.ticker = ticker;
        this.date = date;
        this.price = price;
        this.currency = currency;
        this.market = market;
    }

    public String getId() {
        return id;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    @Override
    public String toString() {
        return "Stock record [id=" + id + ", date=" + date + ", ticker=" + ticker 
            + ", price=" + price + ", currency=" + currency + "]";
    }
}
