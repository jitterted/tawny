package com.jitterted.tawny.adapter.out.pricer;

import com.jitterted.tawny.domain.Contract;
import com.jitterted.tawny.domain.Pricer;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TradierPricer implements Pricer {
  @Override
  public BigDecimal fetchPriceQuote(Contract contract) {
    return null;
  }
}
