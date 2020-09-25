package com.jitterted.tawny.domain;

import java.math.BigDecimal;

public interface Pricer {
  BigDecimal fetchPriceQuote(Contract contract);
}
