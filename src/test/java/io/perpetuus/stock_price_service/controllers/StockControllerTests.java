package io.perpetuus.stock_price_service.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import io.perpetuus.stock_price_service.StockPriceServiceApplication;

@SpringBootTest
@ContextConfiguration(classes = { StockPriceServiceApplication.class })
@WebAppConfiguration
public class StockControllerTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mvc;

    @BeforeEach
    public void setup() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void should_GetStocks_When_CorrectStockTickerParamValuePassed() throws Exception {
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
    public void should_GetStocks_When_CorrectDateParamValuePassed() throws Exception {
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
    
}
