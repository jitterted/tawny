package com.jitterted.tawny.domain;

import org.joda.money.Money;

import java.time.LocalDate;

public class Position {

  private static final int SHARES_PER_OPTION = 100;

  private final Contract contract;

  private final int quantity;
  private final Money unitCost;

  public Position(String underlyingSymbol,
                  String contractType,
                  int quantity,
                  LocalDate expirationDate,
                  int strikePrice,
                  Money unitCost) {
    this.contract = new Contract(underlyingSymbol, contractType, expirationDate, strikePrice);
    this.quantity = quantity;
    this.unitCost = unitCost;
  }

  public int quantity() {
    return quantity;
  }

  public Contract contract() {
    return contract;
  }

  public Money unitCost() {
    return unitCost;
  }

  public Money totalCost() {
    return unitCost.multipliedBy(quantity)
                   .multipliedBy(SHARES_PER_OPTION);
  }

  public Money currentValue(Money lastPrice) {
    return lastPrice.multipliedBy(quantity)
                    .multipliedBy(SHARES_PER_OPTION);
  }
}
