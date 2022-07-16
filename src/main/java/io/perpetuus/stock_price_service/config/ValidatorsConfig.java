package io.perpetuus.stock_price_service.config;

import java.util.ArrayList;
import java.util.List;

public class ValidatorsConfig {
    
    public static final String VALID_DATE_FORMAT = "dd-MM-yyyy";

    public static final ArrayList<String> VALID_CURRENCIES = new ArrayList<>(List.of("PLN", "USD", "EUR", "CHF", "CAD", "JPY", "GBP"));

    public static final String VALID_CURRENCIES_STRING = "PLN, USD, EUR, CHF, CAD, JPY, GBP";

}
