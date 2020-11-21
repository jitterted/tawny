package com.jitterted.tawny.adapter.in.web;

import com.jitterted.tawny.domain.DateConstants;
import com.jitterted.tawny.domain.Portfolio;
import com.jitterted.tawny.domain.Position;
import com.jitterted.tawny.domain.UsMoney;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PositionViewMappingTest {

  @Test
  public void positionIsMappedToView() throws Exception {
    Position position = new Portfolio().openPosition("AMD", "C", 10, DateConstants.OCT_16_2020, 80, UsMoney.$(2));

    PositionView expectedView = new PositionView(
        "AMD", "C", "2020-10-16", "80", "10", "$2.00", "$2,000.00",
        "$0.00", "$0.00", "0", "0",
        false, "0");

    PositionView positionView = PositionView.fromDomain(position, UsMoney.zero());

    assertThat(positionView)
        .isEqualTo(expectedView);
  }

}