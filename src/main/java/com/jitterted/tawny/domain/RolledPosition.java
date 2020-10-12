package com.jitterted.tawny.domain;

public class RolledPosition extends Position {
  private final Position newPosition;
  private final Position positionToRoll;

  public RolledPosition(Position positionToRoll, Position newPosition) {
    super(newPosition.contract().underlyingSymbol(),
          newPosition.contract().contractType(),
          newPosition.quantity(),
          newPosition.contract().expirationDate(),
          newPosition.contract().strikePrice(),
          newPosition.unitCost().minus(positionToRoll.unitGain()));
    this.positionToRoll = positionToRoll;
    this.newPosition = newPosition;
  }
}
