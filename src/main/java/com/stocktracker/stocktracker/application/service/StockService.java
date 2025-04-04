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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StockService {
  private static final Logger logger = LoggerFactory.getLogger(StockService.class);

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
    logger.debug("Fetching stock for symbol {} on date {}", symbol, date);
    Optional<Stock> stock = stockRepository.findByCompanySymbolAndDate(symbol, date);
    if (stock.isEmpty()) {
      logger.info("No stock found for symbol {} on date {}", symbol, date);
    }
    return stock;
  }

  public void fetchFromPolygonAndSaveIfNotExists(String symbol, String from, String to) {
    try {
      logger.info("Fetching stock data for symbol: {} from {} to {}", symbol, from, to);
      PolygonResponse response = polygonClient.fetchStockData(symbol, from, to);

      if (response == null || response.getResults() == null) {
        logger.warn("No data found for symbol {}", symbol);
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
              .filter(
                  stock ->
                      !stockRepository.existsByCompanySymbolAndDate(
                          stock.getCompanySymbol(), stock.getDate()))
              .toList();

      logger.info(
          "Fetched {} records from Polygon. {} new records will be saved.",
          response.getResults().size(),
          stocks.size());

      stockRepository.saveAll(stocks);
      logger.info("Saved {} stock records to database for symbol {}", stocks.size(), symbol);
    } catch (Exception e) {
      logger.error("Error while fetching data from Polygon for symbol {}", symbol, e);
    }
  }

  private LocalDate epochMillisToLocalDate(long epochMillis) {
    return Instant.ofEpochMilli(epochMillis)
        .atZone(ZoneId.of("America/New_York")) // Polygon usa Eastern Time
        .toLocalDate();
  }
}
