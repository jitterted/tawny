package com.jitterted.tawny.domain;

import org.joda.money.Money;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class PositionRollTest {

  @Test
  public void rollPositionUnitCostIncludesGainOfPreviousPosition() throws Exception {
    Money originalOpenCost = UsMoney.$(8.50);
    Position position = new Position("AMD", "C", 1, LocalDate.of(2021, 1, 15), 75,
                                     originalOpenCost);

    Money costToClose = UsMoney.$(17.21);
    Money rolledOpenCost = UsMoney.$(16.98);

    Position rolledPosition = new PositionRoller()
        .roll(position, costToClose, 1, LocalDate.of(2021, 3, 19), 80, rolledOpenCost);

    //8.50 + (16.98 - 17.21) = 8.50 + (-0.23) = 8.27
    Money costToRoll = rolledOpenCost.minus(costToClose);
    Money newCost = originalOpenCost.plus(costToRoll);
    assertThat(rolledPosition.unitCost())
        .isEqualByComparingTo(newCost);
  }

}