package com.jitterted.tawny.domain;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class Position {

  private static final BigDecimal SHARES_PER_OPTION = BigDecimal.valueOf(100);

  private final Contract contract;

  private final int quantity;
  private final int unitCost;

  public Position(String underlyingSymbol,
                  String contractType,
                  int quantity,
                  OffsetDateTime expirationDate,
                  int strikePrice,
                  int unitCost) {
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

  public int unitCost() {
    return unitCost;
  }

  public int totalCost() {
    return unitCost * quantity * SHARES_PER_OPTION.intValue();
  }

  public BigDecimal currentValue(BigDecimal lastPrice) {
    return lastPrice.multiply(BigDecimal.valueOf(quantity))
                    .multiply(SHARES_PER_OPTION);
  }
}
