package com.jitterted.tawny;

import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SuppressWarnings("unchecked")
class PortfolioControllerTest {

  @Test
  public void givenSingleOpenPositionViewReturnsPosition() throws Exception {
    PortfolioController portfolioController = new PortfolioController(List.of(new PositionView(
        "AAPL", "C", "1", "Oct 16 2020", "125.00", "6.40", "640.00",
        "6.35", "635.00", "($5.00)", "(8%)")));
    Model model = new ConcurrentModel();

    portfolioController.viewPortfolio(model);

    Collection<PositionView> positions = (Collection<PositionView>) model.getAttribute("positions");
    PositionView expectedView = new PositionView(
        "AAPL", "C", "1", "Oct 16 2020", "125.00", "6.40", "640.00", "6.35", "635.00", "($5.00)", "(8%)");

    assertThat(positions)
        .contains(expectedView);
  }

  @Test
  public void givenMultipleOpenPositionsViewReturnsAllPositions() throws Exception {
    PositionView aaplPositionView = new PositionView(
        "AAPL", "C", "1", "Oct 16 2020", "125.00", "6.40", "640.00",
        "6.35", "635.00", "($5.00)", "(8%)");
    PositionView amdPositionView = new PositionView(
        "AMD", "C", "10", "Oct 16 2020", "80", "2", "2000", "3", "3000", "1000", "50%"
    );
    PortfolioController portfolioController = new PortfolioController(
        List.of(aaplPositionView, amdPositionView));
    Model model = new ConcurrentModel();

    portfolioController.viewPortfolio(model);

    Collection<PositionView> positions = (Collection<PositionView>) model.getAttribute("positions");

    assertThat(positions)
        .containsExactlyInAnyOrder(aaplPositionView, amdPositionView);
  }

}

