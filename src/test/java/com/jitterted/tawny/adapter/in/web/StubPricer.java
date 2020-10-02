package com.jitterted.tawny.adapter.in.web;

import com.jitterted.tawny.domain.Contract;
import com.jitterted.tawny.domain.Pricer;
import com.jitterted.tawny.domain.UsMoney;
import org.joda.money.Money;

class StubPricer implements Pricer {
  private final Money lastPrice;

  public StubPricer(double lastPriceConstant) {
    lastPrice = UsMoney.$(lastPriceConstant);
  }

  @Override
  public Money fetchPriceQuote(Contract contract) {
    return lastPrice;
  }
}
