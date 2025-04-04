package com.stocktracker.stocktracker.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stocktracker.stocktracker.domain.model.Stock;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class StockIntegrationTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @Test
  void testFullFlow_fetchAndGetStock() throws Exception {
    String symbol = "AAPL";
    String date = "2023-11-06";

    // Ejecuta POST /stocks/fetch
    mockMvc
        .perform(
            post("/stocks/fetch")
                .param("companySymbol", symbol)
                .param("fromDate", date)
                .param("toDate", date))
        .andExpect(status().isOk());

    // Ejecuta GET /stocks/{symbol}?date=YYYY-MM-DD
    String response =
        mockMvc
            .perform(
                get("/stocks/" + symbol).param("date", date).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

    Stock stock = objectMapper.readValue(response, Stock.class);

    assertThat(stock.getCompanySymbol()).isEqualTo(symbol);
    assertThat(stock.getDate()).isEqualTo(LocalDate.parse(date));
    assertThat(stock.getOpenPrice()).isNotNull();
    assertThat(stock.getClosePrice()).isNotNull();
  }
}
