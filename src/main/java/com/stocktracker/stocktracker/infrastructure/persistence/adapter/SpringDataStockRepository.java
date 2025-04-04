package com.stocktracker.stocktracker.infrastructure.persistence.adapter;

import com.stocktracker.stocktracker.infrastructure.persistence.entity.StockEntity;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataStockRepository extends JpaRepository<StockEntity, Integer> {

  Optional<StockEntity> findByCompanySymbolAndDate(String companySymbol, LocalDate date);

  boolean existsByCompanySymbolAndDate(String companySymbol, LocalDate date);
}
