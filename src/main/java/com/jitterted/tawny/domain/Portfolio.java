package com.jitterted.tawny.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

public class Portfolio {
  private final List<Position> positions = new ArrayList<>();
  private AtomicLong sequence = new AtomicLong();

  public static Portfolio of(Position... positions) {
    Portfolio portfolio = new Portfolio();

    Arrays.stream(positions).forEach(portfolio::add);

    return portfolio;
  }

  public void add(Position position) {
    if (position.getId() == null) {
      position.setId(sequence.getAndIncrement());
    }
    positions.add(position);
  }

  public Stream<Position> stream() {
    return positions.stream();
  }

  public Optional<Position> findById(int id) {
    return stream().filter(position -> position.getId() == id)
                   .findFirst();
  }
}
