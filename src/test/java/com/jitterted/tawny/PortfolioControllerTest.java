package com.jitterted.tawny;

import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import java.util.Collection;

import static org.assertj.core.api.Assertions.*;

@SuppressWarnings("unchecked")
class PortfolioControllerTest {

  @Test
  public void givenOpenPositionViewReturnsPosition() throws Exception {
    PortfolioController portfolioController = new PortfolioController();
    Model model = new ConcurrentModel();

    portfolioController.viewPortfolio(model);

    Collection<PositionView> positions = (Collection<PositionView>) model.getAttribute("positions");
    PositionView expectedDto = new PositionView(
        "AAPL", "C", "1", "Oct 16 2020", "125.00", "6.40", "640.00", "6.35", "635.00", "($5.00)", "(8%)");
    assertThat(positions)
        .contains(expectedDto);
  }

}

