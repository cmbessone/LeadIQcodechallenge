package com.stocktracker.stocktracker.infrastructure.persistence.adapter;

import com.stocktracker.stocktracker.domain.model.Stock;
import com.stocktracker.stocktracker.domain.repository.StockRepository;
import com.stocktracker.stocktracker.infrastructure.persistence.entity.StockEntity;
import com.stocktracker.stocktracker.infrastructure.persistence.mapper.StockMapper;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class JpaStockRepository implements StockRepository {

  private final SpringDataStockRepository springDataRepo;

  public JpaStockRepository(SpringDataStockRepository springDataRepo) {
    this.springDataRepo = springDataRepo;
  }

  @Override
  public void saveAll(List<Stock> stocks) {
    List<StockEntity> entities =
        stocks.stream().map(StockMapper::toEntity).collect(Collectors.toList());
    springDataRepo.saveAll(entities);
  }

  @Override
  public Optional<Stock> findByCompanySymbolAndDate(String symbol, LocalDate date) {
    return springDataRepo.findByCompanySymbolAndDate(symbol, date).map(StockMapper::toDomain);
  }

  @Override
  public boolean existsByCompanySymbolAndDate(String companySymbol, LocalDate date) {
    return springDataRepo.existsByCompanySymbolAndDate(companySymbol, date);
  }
}
