package com.jitterted.tawny.domain;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class PositionTotalCostTest {

  private static final LocalDate DUMMY_EXPIRATION = LocalDate.EPOCH;
  private static final int DUMMY_STRIKE_PRICE = 0;
  private static final String DUMMY_SYMBOL = "";
  private static final String DUMMY_OPTION_TYPE = "";
  private static final Money DUMMY_UNIT_COST = UsMoney.zero();

  @Test
  public void openPositionWithQuantity5UnitCost8IsTotalCost4_000() throws Exception {
    int quantity = 5;
    Money unitCost = UsMoney.$(8);
    Position position = new Position(DUMMY_SYMBOL, DUMMY_OPTION_TYPE,
                                     quantity, DUMMY_EXPIRATION, DUMMY_STRIKE_PRICE,
                                     unitCost);

    assertThat(position.totalCost())
        .isEqualTo(UsMoney.$(5 * 8 * 100)); // 5 * 8 * 100
  }

  @Test
  public void positionCalculatesCurrentValueBasedOnLastPrice() throws Exception {
    int quantity = 5;
    Position position = new Position(DUMMY_SYMBOL, DUMMY_OPTION_TYPE,
                                     quantity, DUMMY_EXPIRATION, DUMMY_STRIKE_PRICE,
                                     DUMMY_UNIT_COST);
    Money lastPrice = Money.of(CurrencyUnit.USD, 9);

    assertThat(position.currentValue(lastPrice))
        .isEqualByComparingTo(UsMoney.$(9 * 5 * 100));
  }
}