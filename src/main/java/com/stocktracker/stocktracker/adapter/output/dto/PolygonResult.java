package com.stocktracker.stocktracker.adapter.output.dto;

import java.math.BigDecimal;

public class PolygonResult {
  private BigDecimal c; // close
  private BigDecimal h; // high
  private BigDecimal l; // low
  private BigDecimal o; // open
  private long t; // timestamp (ms)
  private long v; // volume

  public BigDecimal getC() {
    return c;
  }

  public void setC(BigDecimal c) {
    this.c = c;
  }

  public BigDecimal getH() {
    return h;
  }

  public void setH(BigDecimal h) {
    this.h = h;
  }

  public BigDecimal getL() {
    return l;
  }

  public void setL(BigDecimal l) {
    this.l = l;
  }

  public BigDecimal getO() {
    return o;
  }

  public void setO(BigDecimal o) {
    this.o = o;
  }

  public long getT() {
    return t;
  }

  public void setT(long t) {
    this.t = t;
  }

  public long getV() {
    return v;
  }

  public void setV(long v) {
    this.v = v;
  }
}
