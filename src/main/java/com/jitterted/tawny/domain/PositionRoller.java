package com.jitterted.tawny.domain;

import org.joda.money.Money;

import java.time.LocalDate;

public class PositionRoller {
  public Position roll(Position positionToRoll, Money costToClose, int newQuantity,
                       LocalDate newExpiration, int newStrike, Money rolledOpenCost) {
    positionToRoll.close(costToClose);

    Position newPosition = new Position(positionToRoll.contract().underlyingSymbol(),
                                        positionToRoll.contract().contractType(),
                                        newQuantity,
                                        newExpiration,
                                        newStrike,
                                        rolledOpenCost);

    RolledPosition rolledPosition = new RolledPosition(positionToRoll, newPosition);

    return rolledPosition;
  }
}
