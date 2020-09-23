package com.jitterted.tawny;

import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.*;

class PositionTotalCostTest {

  private static final OffsetDateTime DUMMY_EXPIRATION = OffsetDateTime.now();
  private static final int DUMMY_STRIKE_PRICE = 0;
  private static final String DUMMY_SYMBOL = "";
  private static final String DUMMY_OPTION_TYPE = "";

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
}