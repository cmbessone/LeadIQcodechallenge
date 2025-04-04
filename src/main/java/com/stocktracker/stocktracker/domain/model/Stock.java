package com.stocktracker.stocktracker.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Stock {

  private Integer id;
  private String companySymbol;
  private LocalDate date;
  private BigDecimal openPrice;
  private BigDecimal closePrice;
  private BigDecimal highPrice;
  private BigDecimal lowPrice;
  private Long volume;

  public Stock() {}

  public Stock(
      Integer id,
      String companySymbol,
      LocalDate date,
      BigDecimal openPrice,
      BigDecimal closePrice,
      BigDecimal highPrice,
      BigDecimal lowPrice,
      Long volume) {
    this.id = id;
    this.companySymbol = companySymbol;
    this.date = date;
    this.openPrice = openPrice;
    this.closePrice = closePrice;
    this.highPrice = highPrice;
    this.lowPrice = lowPrice;
    this.volume = volume;
  }

  public Stock(
      String companySymbol,
      LocalDate date,
      BigDecimal openPrice,
      BigDecimal closePrice,
      BigDecimal highPrice,
      BigDecimal lowPrice,
      Long volume) {
    this.companySymbol = companySymbol;
    this.date = date;
    this.openPrice = openPrice;
    this.closePrice = closePrice;
    this.highPrice = highPrice;
    this.lowPrice = lowPrice;
    this.volume = volume;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCompanySymbol() {
    return companySymbol;
  }

  public void setCompanySymbol(String companySymbol) {
    this.companySymbol = companySymbol;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public BigDecimal getOpenPrice() {
    return openPrice;
  }

  public void setOpenPrice(BigDecimal openPrice) {
    this.openPrice = openPrice;
  }

  public BigDecimal getClosePrice() {
    return closePrice;
  }

  public void setClosePrice(BigDecimal closePrice) {
    this.closePrice = closePrice;
  }

  public BigDecimal getHighPrice() {
    return highPrice;
  }

  public void setHighPrice(BigDecimal highPrice) {
    this.highPrice = highPrice;
  }

  public BigDecimal getLowPrice() {
    return lowPrice;
  }

  public void setLowPrice(BigDecimal lowPrice) {
    this.lowPrice = lowPrice;
  }

  public Long getVolume() {
    return volume;
  }

  public void setVolume(Long volume) {
    this.volume = volume;
  }
}
