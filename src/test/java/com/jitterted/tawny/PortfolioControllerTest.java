package com.jitterted.tawny;

import com.jitterted.tawny.domain.Portfolio;
import org.junit.jupiter.api.Disabled;
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
    Portfolio portfolio = Portfolio.of(aaplPosition);
    PortfolioController portfolioController = new PortfolioController(portfolio);
    Collection<PositionView> positions = positionsFromViewModel(portfolioController);
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
    Portfolio portfolio = Portfolio.of(aaplPosition, amdPosition);
    PortfolioController portfolioController = new PortfolioController(portfolio);

    Collection<PositionView> positions = positionsFromViewModel(portfolioController);

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

  @Test
  public void submitOpenPositionThenViewShowsPosition() throws Exception {
    PortfolioController portfolioController = new PortfolioController(new Portfolio());
    OpenPositionForm openPositionForm = new OpenPositionForm();
    openPositionForm.setUnderlyingSymbol("AMD");
    openPositionForm.setExpiration(OCT_16_2020);
    openPositionForm.setQuantity(10);
    openPositionForm.setOptionType("C");
    openPositionForm.setStrikePrice(80);
    openPositionForm.setUnitCost(2);
    portfolioController.handleOpenPosition(openPositionForm);

    Collection<PositionView> positionViews = positionsFromViewModel(portfolioController);

    PositionView amdPositionView = new PositionView(
        "AMD", "C", "10", "2020-10-16T16:00-04:00", "80", "2", "2000",
        "0.00", "0.00", "0", "0"
    );

    assertThat(positionViews)
        .containsOnly(amdPositionView);
  }

  @Disabled("Until we have separated out the list of positions into its own repository")
  @Test
  public void currentValueOfPositionComesFromPricerPort() throws Exception {
    Position amdPosition = new Position("AMD", "C", 10, OCT_16_2020, 80, 2);
    Portfolio portfolio = Portfolio.of(amdPosition);
    PortfolioController portfolioController = new PortfolioController(portfolio);

  }

  private Collection<PositionView> positionsFromViewModel(PortfolioController portfolioController) {
    Model model = new ConcurrentModel();
    portfolioController.viewPortfolio(model);

    return (Collection<PositionView>) model.getAttribute("positions");
  }

}

