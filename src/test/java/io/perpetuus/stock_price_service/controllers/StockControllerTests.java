package io.perpetuus.stock_price_service.controllers;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import io.perpetuus.stock_price_service.StockPriceServiceApplication;
import io.perpetuus.stock_price_service.models.StockRecord;
import io.perpetuus.stock_price_service.services.StockRecordService;

@SpringBootTest
@ContextConfiguration(classes = { StockPriceServiceApplication.class })
@WebAppConfiguration
public class StockControllerTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private StockRecordService stockRecordService;

    private MockMvc mvc;

    @BeforeEach
    public void setup() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void should_GetStocks_When_CorrectStockTickerParamValuePassed() throws Exception {
        // given
        var stocks = new ArrayList<StockRecord>();
        var stock = new StockRecord("1", "PZU", "02-10-2022", BigDecimal.ONE, "PLN", "WSE");
        stocks.add(stock);
        when(stockRecordService.findByTicker("PZU")).thenReturn(stocks);

        // when
        var req = MockMvcRequestBuilders.get("/stock/historical?stockTicker={stockTicker}", "PZU");

        // then
        mvc.perform(req).andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$").hasJsonPath())
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].ticker").value("PZU"))
            .andExpect(jsonPath("$[0].date").value("02-10-2022"))
            .andExpect(jsonPath("$[0].price").value(1))
            .andExpect(jsonPath("$[0].currency").value("PLN"))
            .andExpect(jsonPath("$[0].market").value("WSE"));
    }

    @Test
    public void should_ThrowException_When_NoStockTickerParamPassed() throws Exception {
        // when
        var req = MockMvcRequestBuilders.get("/stock/historical");

        // then
        mvc.perform(req).andDo(MockMvcResultHandlers.print())
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.response").hasJsonPath())
            .andExpect(jsonPath("$.response", hasSize(1)));
    }

    @Test
    public void should_ThrowException_When_NoStockTickerParamValuePassed() throws Exception {
        // when
        var req = MockMvcRequestBuilders.get("/stock/historical?stockTicker=");

        // then
        mvc.perform(req).andDo(MockMvcResultHandlers.print())
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.response").hasJsonPath())
            .andExpect(jsonPath("$.response", hasSize(2)));
    }

    @Test
    public void should_GetStocks_When_CorrectDateParamValuePassed() throws Exception {
        // given
        var stocks = new ArrayList<StockRecord>();
        var stock = new StockRecord("1", "PZU", "02-10-2022", BigDecimal.ONE, "PLN", "WSE");
        stocks.add(stock);
        when(stockRecordService.findByDate("02-10-2022")).thenReturn(stocks);

        // when
        var req = MockMvcRequestBuilders.get("/stock/daily?date={date}", "02-10-2022");

        // then
        mvc.perform(req).andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$").hasJsonPath())
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].ticker").value("PZU"))
            .andExpect(jsonPath("$[0].date").value("02-10-2022"))
            .andExpect(jsonPath("$[0].price").value(1))
            .andExpect(jsonPath("$[0].currency").value("PLN"))
            .andExpect(jsonPath("$[0].market").value("WSE"));
    }

    @Test
    public void should_ThrowException_When_NoDateParamPassed() throws Exception {
        // when
        var req = MockMvcRequestBuilders.get("/stock/daily");

        // then
        mvc.perform(req).andDo(MockMvcResultHandlers.print())
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.response").hasJsonPath())
            .andExpect(jsonPath("$.response", hasSize(2)));
    }

    @Test
    public void should_ThrowException_When_NoDateParamValuePassed() throws Exception {
        // when
        var req = MockMvcRequestBuilders.get("/stock/daily?date=");

        // then
        mvc.perform(req).andDo(MockMvcResultHandlers.print())
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.response").hasJsonPath())
            .andExpect(jsonPath("$.response", hasSize(2)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"10-23-2022", "10-Jan-2022", "10/01/2022", "3-20-2022", "2022-10-30"})
    public void should_ThrowException_When_IncorrectDateParamValuePassed(String dateString) throws Exception {
        // when
        var req = MockMvcRequestBuilders.get("/stock/daily?date={date}", dateString); 

        // then
        mvc.perform(req).andDo(MockMvcResultHandlers.print())
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.response").hasJsonPath())
            .andExpect(jsonPath("$.response", hasSize(1)));
    }

    @Test
    public void should_CreateStockRecord_When_RequestBodyIsValid() throws Exception {
        // given
        var content = "{ \"ticker\": \"AMD\", \"date\": \"10-02-2022\", \"price\": 12.32, \"currency\": \"USD\", \"market\": \"WSE\" }";

        // when
        var req = MockMvcRequestBuilders.post("/stock")
            .contentType(MediaType.APPLICATION_JSON)
            .content(content)
            .accept(MediaType.APPLICATION_JSON);

        // then
        mvc.perform(req).andDo(MockMvcResultHandlers.print())
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.response").hasJsonPath());

    }
    
}
