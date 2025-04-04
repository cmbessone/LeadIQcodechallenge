package com.stocktracker.stocktracker.domain.repository;

import com.stocktracker.stocktracker.domain.model.Stock;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface StockRepository {
  void saveAll(List<Stock> stocks);

  Optional<Stock> findByCompanySymbolAndDate(String symbol, LocalDate date);
}
