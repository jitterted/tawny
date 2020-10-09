package com.jitterted.tawny.adapter.in.web;

import com.jitterted.tawny.domain.Contract;
import com.jitterted.tawny.domain.Pricer;
import org.joda.money.Money;

public class DummyPricer implements Pricer {
  @Override
  public Money fetchPriceQuote(Contract contract) {
    throw new UnsupportedOperationException();
  }
}
