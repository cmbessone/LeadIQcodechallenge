package com.stocktracker.stocktracker.adapter.input;

import com.stocktracker.stocktracker.application.service.StockService;
import com.stocktracker.stocktracker.domain.model.Stock;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stocks")
public class StockController {

  private final StockService stockService;

  public StockController(StockService stockService) {
    this.stockService = stockService;
  }

  // ✅ POST /stocks/fetch
  @PostMapping("/fetch")
  public void fetchFromPolygon(
      @RequestParam String companySymbol,
      @RequestParam String fromDate,
      @RequestParam String toDate) {
    stockService.fetchAndSave(companySymbol, fromDate, toDate);
  }

  // ✅ GET /stocks/{symbol}?date=YYYY-MM-DD
  @GetMapping("/{symbol}")
  public Optional<Stock> getBySymbolAndDate(
      @PathVariable String symbol, @RequestParam("date") LocalDate date) {
    return stockService.getByCompanySymbolAndDate(symbol, date);
  }
}
