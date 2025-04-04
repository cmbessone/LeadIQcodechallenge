package com.stocktracker.stocktracker.application.service;

import com.stocktracker.stocktracker.adapter.output.PolygonClient;
import com.stocktracker.stocktracker.adapter.output.dto.PolygonResponse;
import com.stocktracker.stocktracker.domain.model.Stock;
import com.stocktracker.stocktracker.domain.repository.StockRepository;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

public class StockService {

  private final StockRepository stockRepository;
  private final PolygonClient polygonClient;

  public StockService(StockRepository stockRepository, PolygonClient polygonClient) {
    this.stockRepository = stockRepository;
    this.polygonClient = polygonClient;
  }

  public void saveStock(List<Stock> stocks) {
    stockRepository.saveAll(stocks);
  }

  public Optional<Stock> getByCompanySymbolAndDate(String symbol, LocalDate date) {
    return stockRepository.findByCompanySymbolAndDate(symbol, date);
  }

  public void fetchAndSave(String symbol, String from, String to) {
    PolygonResponse response = polygonClient.fetchStockData(symbol, from, to);

    if (response == null || response.getResults() == null) {
      System.out.println("No data found for symbol ");
      return;
    }

    List<Stock> stocks =
        response.getResults().stream()
            .map(
                result ->
                    new Stock(
                        symbol,
                        epochMillisToLocalDate(result.getT()),
                        result.getO(),
                        result.getC(),
                        result.getH(),
                        result.getL(),
                        result.getV()))
            .toList();
    stockRepository.saveAll(stocks);
  }

  private LocalDate epochMillisToLocalDate(long epochMillis) {
    return Instant.ofEpochMilli(epochMillis)
        .atZone(ZoneId.of("America/New_York")) // Polygon usa Eastern Time
        .toLocalDate();
  }
}
