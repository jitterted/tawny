package com.jitterted.tawny.domain;

import org.jetbrains.annotations.NotNull;
import org.joda.money.Money;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class PositionCloseTest {

  @Test
  public void btoPositionWhenClosedHasGainOfDifference() throws Exception {
    Money openUnitCost = UsMoney.$(8.50);
    Position position = createOpenPositionWithUnitCostOf(openUnitCost);

    Money closeUnitCost = UsMoney.$(9.25);
    position.close(closeUnitCost);

    assertThat(position.unitGain())
        .isEqualByComparingTo(closeUnitCost.minus(openUnitCost));
  }

  @Test
  public void positionWhenClosedIsClosed() throws Exception {
    Position position = createOpenPositionWithUnitCostOf(UsMoney.$(1));

    position.close(UsMoney.$(2));

    assertThat(position.isClosed())
        .isTrue();
  }

  @Test
  public void positionIsNotClosedWhenCreated() throws Exception {
    Position openPosition = createOpenPositionWithUnitCostOf(UsMoney.$(5));

    assertThat(openPosition.isClosed())
        .isFalse();
  }

  @NotNull
  private Position createOpenPositionWithUnitCostOf(Money unitCost) {
    return new Position("AMD", "C", 1, DateConstants.OCT_16_2020,
                        75, unitCost);
  }

}
