package com.jitterted.tawny;

import java.math.BigDecimal;

public interface Pricer {
  BigDecimal fetchPriceQuote(String symbol);
}
