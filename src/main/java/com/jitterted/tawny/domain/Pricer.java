package com.jitterted.tawny.domain;

import org.joda.money.Money;

// PORT in Hexagonal Architecture
public interface Pricer {
  Money fetchPriceQuote(Contract contract);
}
