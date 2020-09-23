package com.jitterted.tawny;

import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Collection;

import static org.assertj.core.api.Assertions.*;

@SuppressWarnings("unchecked")
class PortfolioControllerTest {

  private static final OffsetDateTime OCT_16_2020 = OffsetDateTime.of(2020, 10, 16, 16, 0, 0, 0, ZoneOffset.of("-04:00"));

  @Test
  public void givenSingleOpenPositionViewReturnsPosition() throws Exception {
    Position aaplPosition = new Position("AAPL", "C", 1, OCT_16_2020, 125, 6);
    PortfolioController portfolioController = new PortfolioController(aaplPosition);
    Model model = new ConcurrentModel();

    portfolioController.viewPortfolio(model);

    Collection<PositionView> positions = (Collection<PositionView>) model.getAttribute("positions");
    PositionView expectedView = new PositionView(
        "AAPL", "C", "1", "2020-10-16T16:00-04:00", "125", "6", "600",
        "0.00", "0.00", "0", "0");

    assertThat(positions)
        .contains(expectedView);
  }

  @Test
  public void givenMultipleOpenPositionsViewReturnsAllPositions() throws Exception {
    Position aaplPosition = new Position("AAPL", "C", 1, OCT_16_2020, 125, 6);
    Position amdPosition = new Position("AMD", "C", 10, OCT_16_2020, 80, 2);
    PortfolioController portfolioController = new PortfolioController(aaplPosition, amdPosition);

    Model model = new ConcurrentModel();
    portfolioController.viewPortfolio(model);

    Collection<PositionView> positions = (Collection<PositionView>) model.getAttribute("positions");

    PositionView aaplPositionView = new PositionView(
        "AAPL", "C", "1", "2020-10-16T16:00-04:00", "125", "6", "600",
        "0.00", "0.00", "0", "0");
    PositionView amdPositionView = new PositionView(
        "AMD", "C", "10", "2020-10-16T16:00-04:00", "80", "2", "2000",
        "0.00", "0.00", "0", "0"
    );
    assertThat(positions)
        .containsExactlyInAnyOrder(aaplPositionView, amdPositionView);
  }

}

