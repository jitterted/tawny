package com.jitterted.tawny.domain;

import org.joda.money.Money;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class PositionRollTest {

  private static final LocalDate EXPIRATION = LocalDate.of(2021, 1, 15);

  @Test
  public void rollPositionUnitCostIncludesGainOfPreviousPosition() throws Exception {
    Portfolio portfolio = new Portfolio();

    Money originalOpenCost = UsMoney.$(8);
    Position position = new Position("AMD", "C", 1, EXPIRATION, 75, originalOpenCost);

    Money costToClose = UsMoney.$(17);
    Money rolledOpenCost = UsMoney.$(16);

    Position rolledPosition = portfolio
        .roll(position, costToClose, 1, EXPIRATION, 80, rolledOpenCost);

    Money costToRoll = rolledOpenCost.minus(costToClose);
    Money newCost = originalOpenCost.plus(costToRoll);
    assertThat(rolledPosition.unitCost())
        .isEqualByComparingTo(newCost);
  }

  @Test
  public void rollTwiceAccumulatesGainAcrossAllPositions() throws Exception {
    Portfolio portfolio = new Portfolio();
    int qty = 1;

    Money pos1openCost = UsMoney.$(1); // NET COST = 1
    Position position1 = portfolio.openPosition("AMD", "C", qty, EXPIRATION, 75, pos1openCost);

    Money pos1closeCost = UsMoney.$(4); // GAIN = 3
    Money pos2openCost = UsMoney.$(7); // net cost = 7 - 3 = 4
    Position position2 = portfolio
        .roll(position1, pos1closeCost, qty, EXPIRATION, 80, pos2openCost);

    Money pos2closeCost = UsMoney.$(11); // GAIN = 11 - 7 = 4
    Money pos3openCost = UsMoney.$(17);
    Position position3 = portfolio
        .roll(position2, pos2closeCost, qty, EXPIRATION, 85, pos3openCost);

    assertThat(position3.unitCost())
        .isEqualByComparingTo(UsMoney.$(10));
  }

}