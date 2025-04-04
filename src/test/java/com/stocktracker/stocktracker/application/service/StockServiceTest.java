package com.stocktracker.stocktracker.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import com.stocktracker.stocktracker.adapter.output.PolygonClient;
import com.stocktracker.stocktracker.adapter.output.dto.PolygonResponse;
import com.stocktracker.stocktracker.adapter.output.dto.PolygonResult;
import com.stocktracker.stocktracker.domain.model.Stock;
import com.stocktracker.stocktracker.domain.repository.StockRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;

public class StockServiceTest {
  @Test
  public void testGetByCompanySymbolAndDateReturnsStock() {
    StockRepository mockRepo = mock(StockRepository.class);
    PolygonClient dummyClient = mock(PolygonClient.class);
    StockService stockService = new StockService(mockRepo, dummyClient);

    String symbol = "AAPL";
    LocalDate date = LocalDate.of(2023, 11, 6);
    Stock fakeStock =
        new Stock(
            symbol,
            date,
            BigDecimal.valueOf(100.25),
            BigDecimal.valueOf(110.25),
            BigDecimal.valueOf(115.25),
            BigDecimal.valueOf(95),
            10000000L);

    when(mockRepo.findByCompanySymbolAndDate(symbol, date)).thenReturn(Optional.of(fakeStock));

    Optional<Stock> result = stockService.getByCompanySymbolAndDate(symbol, date);

    assertThat(result).isPresent();
    assertThat(result.get().getCompanySymbol()).isEqualTo("AAPL");
    assertThat(result.get().getOpenPrice()).isEqualTo(BigDecimal.valueOf(100.25));
    assertThat(result.get().getDate()).isEqualTo(date);

    verify(mockRepo, times(1)).findByCompanySymbolAndDate(symbol, date);
  }

  @Test
  public void testFetchAndSaveStoresNewStocks() {

    StockRepository mockRepo = mock(StockRepository.class);
    PolygonClient mockClient = mock(PolygonClient.class);
    StockService stockService = new StockService(mockRepo, mockClient);

    String symbol = "AAPL";
    String fromDate = "2023-11-06";
    String toDate = "2023-11-06";
    LocalDate date = LocalDate.of(2023, 11, 6);

    PolygonResult mockResult = new PolygonResult();
    mockResult.setT(date.atStartOfDay(ZoneId.of("America/New_York")).toInstant().toEpochMilli());
    mockResult.setO(BigDecimal.valueOf(100.25));
    mockResult.setC(BigDecimal.valueOf(110.25));
    mockResult.setH(BigDecimal.valueOf(115.25));
    mockResult.setL(BigDecimal.valueOf(95.00));
    mockResult.setV(100000L);

    PolygonResponse mockResponse = new PolygonResponse();
    mockResponse.setResults(List.of(mockResult));

    when(mockClient.fetchStockData(symbol, fromDate, toDate)).thenReturn(mockResponse);
    when(mockRepo.existsByCompanySymbolAndDate(symbol, date)).thenReturn(false);

    stockService.fetchFromPolygonAndSaveIfNotExists(symbol, fromDate, toDate);

    verify(mockRepo, times(1)).saveAll(any());
    verify(mockRepo).existsByCompanySymbolAndDate(symbol, date);
    verify(mockClient).fetchStockData(symbol, fromDate, toDate);
  }

  @Test
  public void testFetchFromPolygonAndSaveIfNotExists_doesNotSaveIfExists() {

    StockRepository mockRepo = mock(StockRepository.class);
    PolygonClient mockClient = mock(PolygonClient.class);
    StockService stockService = new StockService(mockRepo, mockClient);

    String symbol = "AAPL";
    String dateStr = "2023-11-06";
    LocalDate date = LocalDate.parse(dateStr);

    long epochMillis =
        date.atStartOfDay()
            .atZone(java.time.ZoneId.of("America/New_York"))
            .toInstant()
            .toEpochMilli();

    PolygonResult result = new PolygonResult();
    result.setT(epochMillis);
    result.setO(BigDecimal.valueOf(100.0));
    result.setC(BigDecimal.valueOf(110.0));
    result.setH(BigDecimal.valueOf(115.0));
    result.setL(BigDecimal.valueOf(95.0));
    result.setV(1000000L);

    PolygonResponse response = new PolygonResponse();
    response.setResults(Collections.singletonList(result));
    response.setStatus("OK");
    response.setTicker(symbol);
    response.setResultsCount(1);

    when(mockClient.fetchStockData(symbol, dateStr, dateStr)).thenReturn(response);
    when(mockRepo.existsByCompanySymbolAndDate(symbol, date))
        .thenReturn(true); // Simula que ya existe

    stockService.fetchFromPolygonAndSaveIfNotExists(symbol, dateStr, dateStr);

    verify(mockRepo, times(1)).existsByCompanySymbolAndDate(symbol, date);
    verify(mockRepo, never()).saveAll(anyList()); // 👈 Este es el punto clave del test
  }
}
