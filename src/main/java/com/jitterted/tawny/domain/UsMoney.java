package com.jitterted.tawny.domain;

import org.jetbrains.annotations.NotNull;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.math.BigDecimal;

public class UsMoney {
  @NotNull
  public static Money $(double lastPriceConstant) {
    return Money.of(CurrencyUnit.USD, lastPriceConstant);
  }

  public static Money zero() {
    return $(0);
  }

  public static Money $(BigDecimal bigDecimal) {
    return Money.of(CurrencyUnit.USD, bigDecimal);
  }
}
