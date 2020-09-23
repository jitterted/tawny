package com.jitterted.tawny;

import java.time.OffsetDateTime;

public class Position {

  private static final int SHARES_PER_OPTION = 100;

  private final String underlyingSymbol;
  private final String optionType;
  private final int quantity;
  private final OffsetDateTime expirationDate;
  private final int strikePrice;
  private final int unitCost;

  public Position(String underlyingSymbol,
                  String optionType,
                  int quantity,
                  OffsetDateTime expirationDate,
                  int strikePrice,
                  int unitCost) {

    this.underlyingSymbol = underlyingSymbol;
    this.optionType = optionType;
    this.quantity = quantity;
    this.expirationDate = expirationDate;
    this.strikePrice = strikePrice;
    this.unitCost = unitCost;
  }

  public String underlyingSymbol() {
    return underlyingSymbol;
  }

  public String optionType() {
    return optionType;
  }

  public int quantity() {
    return quantity;
  }

  public OffsetDateTime expiration() {
    return expirationDate;
  }

  public int strikePrice() {
    return strikePrice;
  }

  public int unitCost() {
    return unitCost;
  }

  public int totalCost() {
    return unitCost * quantity * SHARES_PER_OPTION;
  }
}
