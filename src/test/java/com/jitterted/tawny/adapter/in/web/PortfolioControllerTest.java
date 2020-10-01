package com.jitterted.tawny.adapter.in.web;

import com.jitterted.tawny.domain.NewYorkTimeConstants;
import com.jitterted.tawny.domain.Portfolio;
import com.jitterted.tawny.domain.Position;
import com.jitterted.tawny.domain.Pricer;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Collection;

import static org.assertj.core.api.Assertions.*;

@SuppressWarnings("unchecked")
class PortfolioControllerTest {

  private static final Pricer STUB_0_00_PRICER = symbol -> new BigDecimal("0.00");

  @Test
  public void givenSingleOpenPositionViewReturnsPosition() throws Exception {
    Position aaplPosition = new Position("AAPL", "C", 1, NewYorkTimeConstants.OCT_16_2020, 125, 6);
    Portfolio portfolio = Portfolio.of(aaplPosition);
    PortfolioController portfolioController = new PortfolioController(portfolio, STUB_0_00_PRICER);
    Collection<PositionView> positions = positionsFromViewModel(portfolioController);
    PositionView expectedView = new PositionView(
        "AAPL", "C", "1", "2020-10-16T16:00-04:00", "125", "6", "600",
        "0.00", "0.00", "0", "0");

    assertThat(positions)
        .contains(expectedView);
  }

  @Test
  public void givenMultipleOpenPositionsViewReturnsAllPositions() throws Exception {
    Position aaplPosition = new Position("AAPL", "C", 1, NewYorkTimeConstants.OCT_16_2020, 125, 6);
    Position amdPosition = new Position("AMD", "C", 10, NewYorkTimeConstants.OCT_16_2020, 80, 2);
    Portfolio portfolio = Portfolio.of(aaplPosition, amdPosition);
    PortfolioController portfolioController = new PortfolioController(portfolio, STUB_0_00_PRICER);

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
    PortfolioController portfolioController = new PortfolioController(new Portfolio(), STUB_0_00_PRICER);
    OpenPositionForm openPositionForm = new OpenPositionForm();
    openPositionForm.setUnderlyingSymbol("AMD");
    openPositionForm.setExpiration(NewYorkTimeConstants.OCT_16_2020);
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

  @Test
  public void currentValueOfPositionComesFromPricerPort() throws Exception {
    int quantity = 10;
    Position amdPosition = new Position("AMD", "C", quantity, NewYorkTimeConstants.OCT_16_2020, 80, 2);
    Portfolio portfolio = Portfolio.of(amdPosition);
    int lastPriceForOption = 3;
    StubPricer stubPricer = new StubPricer(lastPriceForOption);
    PortfolioController portfolioController = new PortfolioController(portfolio, stubPricer);

    Collection<PositionView> positionViews = positionsFromViewModel(portfolioController);

    assertThat(positionViews.stream().findFirst().get().getCurrentValue())
        .isEqualTo(String.valueOf(quantity * lastPriceForOption * 100));
  }

  private Collection<PositionView> positionsFromViewModel(PortfolioController portfolioController) {
    Model model = new ConcurrentModel();
    portfolioController.viewPortfolio(model);

    return (Collection<PositionView>) model.getAttribute("positions");
  }

}

