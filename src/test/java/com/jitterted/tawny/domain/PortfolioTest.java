package com.jitterted.tawny.domain;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class PortfolioTest {

  @Test
  public void addedPositionGetsId() throws Exception {
    Portfolio portfolio = new Portfolio();

    portfolio.add(createDummyPosition());
    Long id = portfolio.stream().findFirst().get().getId();

    assertThat(id)
        .isNotNull();
  }

  @Test
  public void findPositionByIdReturnsMatchingPosition() throws Exception {
    Portfolio portfolio = new Portfolio();
    portfolio.add(new Position("DUMMY", "P", 0, null, 0, null));
    portfolio.add(new Position("TED", "C", 5, DateConstants.OCT_16_2020,
                               125, UsMoney.$(15)));

    Optional<Position> positionFoundById = portfolio.findById(1L);

    assertThat(positionFoundById)
        .isNotEmpty();

    assertThat(positionFoundById.get().contract().underlyingSymbol())
        .isEqualTo("TED");
  }

  @NotNull
  private Position createDummyPosition() {
    return new Position("", "", 0, null, 0, null);
  }

}