package com.jitterted.tawny.domain;

import org.joda.money.Money;

import java.time.LocalDate;
import java.util.Objects;

public class Position {

  private static final int SHARES_PER_OPTION = 100;

  private Long id;

  private final Contract contract;

  private final int quantity;
  private final Money unitCost;
  private Money closeCost;

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

  public void close(Money closeCost) {
    this.closeCost = closeCost;
  }

  public Money unitGain() {
    return closeCost.minus(unitCost);
  }

  public boolean isClosed() {
    return closeCost != null;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Position position = (Position) o;

    return Objects.equals(id, position.id);
  }

  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : -1;
  }
}
