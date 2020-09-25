package com.jitterted.tawny.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.*;

class PositionTotalCostTest {

  private static final OffsetDateTime DUMMY_EXPIRATION = OffsetDateTime.now();
  private static final int DUMMY_STRIKE_PRICE = 0;
  private static final String DUMMY_SYMBOL = "";
  private static final String DUMMY_OPTION_TYPE = "";
  private static final int DUMMY_UNIT_COST = 0;

  @Test
  public void openPositionWithQuantity5UnitCost8IsTotalCost4_000() throws Exception {
    int quantity = 5;
    int unitCost = 8;
    Position position = new Position(DUMMY_SYMBOL, DUMMY_OPTION_TYPE,
                                     quantity, DUMMY_EXPIRATION, DUMMY_STRIKE_PRICE,
                                     unitCost);

    assertThat(position.totalCost())
        .isEqualTo(quantity * unitCost * 100);
  }

  @Test
  public void positionCalculatesCurrentValueBasedOnLastPrice() throws Exception {
    int quantity = 5;
    Position position = new Position(DUMMY_SYMBOL, DUMMY_OPTION_TYPE,
                                     quantity, DUMMY_EXPIRATION, DUMMY_STRIKE_PRICE,
                                     DUMMY_UNIT_COST);
    BigDecimal lastPrice = BigDecimal.valueOf(9);

    assertThat(position.currentValue(lastPrice))
        .isEqualByComparingTo("4500"); // 9 * 5 * 100
  }
}