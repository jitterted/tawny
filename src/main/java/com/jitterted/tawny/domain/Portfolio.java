package com.jitterted.tawny.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Portfolio {
  private final List<Position> positions = new ArrayList<>();

  public static Portfolio of(Position... positions) {
    Portfolio portfolio = new Portfolio();

    Arrays.stream(positions).forEach(portfolio::add);

    return portfolio;
  }

  public void add(Position position) {
    positions.add(position);
  }

  public Stream<Position> stream() {
    return positions.stream();
  }
}
