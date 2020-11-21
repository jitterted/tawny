package com.jitterted.tawny.domain;

import org.joda.money.Money;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

public class Portfolio {
  private final List<Position> positions = new ArrayList<>();
  private final AtomicLong sequence = new AtomicLong();

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
    add(rolledPosition);

    return rolledPosition;
  }

  public Position openPosition(String symbol, String contractType, int quantity, LocalDate expiration, int strikePrice, Money unitCost) {
    Position position = new Position(symbol, contractType, quantity, expiration, strikePrice, unitCost);
    add(position);
    return position;
  }

  void add(Position position) {
    if (position.getId() == null) {
      position.setId(sequence.getAndIncrement());
    }
    positions.add(position);
  }

  public Stream<Position> stream() {
    return positions.stream();
  }

  public Optional<Position> findById(long id) {
    return stream().filter(position -> position.getId() == id)
                   .findFirst();
  }
}
