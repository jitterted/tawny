package com.jitterted.tawny.adapter.in.web;

import com.jitterted.tawny.domain.Pricer;

import java.math.BigDecimal;

class StubPricer implements Pricer {
  private final BigDecimal lastPrice;

  public StubPricer(int lastPriceConstant) {
    lastPrice = BigDecimal.valueOf(lastPriceConstant);
  }

  @Override
  public BigDecimal fetchPriceQuote(String symbol) {
    return lastPrice;
  }
}
