package com.stocktracker.stocktracker.adapter.output.dto;

import java.util.List;

public class PolygonResponse {
  private String ticker;
  private String status;
  private int resultsCount;
  private List<PolygonResult> results;

  public String getTicker() {
    return ticker;
  }

  public void setTicker(String ticker) {
    this.ticker = ticker;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public int getResultsCount() {
    return resultsCount;
  }

  public void setResultsCount(int resultsCount) {
    this.resultsCount = resultsCount;
  }

  public List<PolygonResult> getResults() {
    return results;
  }

  public void setResults(List<PolygonResult> results) {
    this.results = results;
  }
}
