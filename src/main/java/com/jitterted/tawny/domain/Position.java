package com.jitterted.tawny.domain;

import org.joda.money.Money;

import java.time.LocalDate;
import java.util.Optional;

public class Position {

  private static final int SHARES_PER_OPTION = 100;

  private final Contract contract;

  private final int quantity;
  private final Money unitCost;
  private Optional<Position> previousPosition = Optional.empty();
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

  public Position(Position previousPosition, int newQuantity, LocalDate newExpiration, int newStrike, Money rolledOpenCost) {
    this(previousPosition.contract().underlyingSymbol(),
         previousPosition.contract().contractType(),
         newQuantity,
         newExpiration,
         newStrike,
         rolledOpenCost
         );
    this.previousPosition = Optional.of(previousPosition);
  }

  public int quantity() {
    return quantity;
  }

  public Contract contract() {
    return contract;
  }

  public Money unitCost() {
    Money netCost = previousPosition.map(Position::unitGain)
                                    .orElse(UsMoney.zero());
    return unitCost.minus(netCost);
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
}
