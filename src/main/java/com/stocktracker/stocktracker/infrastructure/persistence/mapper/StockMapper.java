package com.stocktracker.stocktracker.infrastructure.persistence.mapper;

import com.stocktracker.stocktracker.domain.model.Stock;
import com.stocktracker.stocktracker.infrastructure.persistence.entity.StockEntity;

public class StockMapper {

  public static StockEntity toEntity(Stock stock) {

    StockEntity entity = new StockEntity();
    entity.setId(stock.getId());
    entity.setCompanySymbol(stock.getCompanySymbol());
    entity.setDate(stock.getDate());
    entity.setOpenPrice(stock.getOpenPrice());
    entity.setHighPrice(stock.getHighPrice());
    entity.setLowPrice(stock.getLowPrice());
    entity.setVolume(stock.getVolume());

    return entity;
  }

  public static Stock toDomain(StockEntity entity) {

    Stock stock = new Stock();
    stock.setId(entity.getId());
    stock.setCompanySymbol(entity.getCompanySymbol());
    stock.setDate(entity.getDate());
    stock.setOpenPrice(entity.getOpenPrice());
    stock.setHighPrice(entity.getHighPrice());
    stock.setLowPrice(entity.getLowPrice());

    stock.setVolume(entity.getVolume());
    return stock;
  }
}
