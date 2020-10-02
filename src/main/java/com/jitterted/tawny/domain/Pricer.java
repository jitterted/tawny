package com.jitterted.tawny.domain;

import org.joda.money.Money;

public interface Pricer {
  Money fetchPriceQuote(Contract contract);
}
