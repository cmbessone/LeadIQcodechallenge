package com.stocktracker.stocktracker.configuration;

import com.stocktracker.stocktracker.adapter.output.PolygonClient;
import com.stocktracker.stocktracker.application.service.StockService;
import com.stocktracker.stocktracker.domain.repository.StockRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StockServiceConfiguration {
  @Bean
  public StockService stockService(StockRepository repository, PolygonClient polygonClient) {
    return new StockService(repository, polygonClient);
  }
}
