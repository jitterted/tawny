package com.jitterted.tawny.adapter.in.web;

import com.jitterted.tawny.domain.DateConstants;
import com.jitterted.tawny.domain.Portfolio;
import com.jitterted.tawny.domain.Position;
import com.jitterted.tawny.domain.Pricer;
import com.jitterted.tawny.domain.UsMoney;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import java.math.BigDecimal;
import java.util.Collection;

import static org.assertj.core.api.Assertions.*;

@SuppressWarnings("unchecked")
class PortfolioControllerTest {

  private static final Pricer STUB_0_00_PRICER = symbol -> UsMoney.zero();

  @MockBean
  private ExpirationsFetcher dummyExpirationsFetcher;

  @Test
  public void givenSingleOpenPositionViewReturnsPosition() throws Exception {
    Position aaplPosition = new Position("AAPL", "C", 1, DateConstants.OCT_16_2020, 125, UsMoney.$(6));
    Portfolio portfolio = Portfolio.of(aaplPosition);
    PortfolioController portfolioController = new PortfolioController(portfolio, STUB_0_00_PRICER, dummyExpirationsFetcher);
    Collection<PositionView> positions = positionsFromViewModel(portfolioController);
    PositionView expectedView = new PositionView(
        "AAPL", "C", "1", "2020-10-16", "125", "$6.00", "$600.00",
        "$0.00", "$0.00", "0", "0");

    assertThat(positions)
        .contains(expectedView);
  }

  @Test
  public void givenMultipleOpenPositionsViewReturnsAllPositions() throws Exception {
    Position aaplPosition = new Position("AAPL", "C", 1, DateConstants.OCT_16_2020, 125, UsMoney.$(6));
    Position amdPosition = new Position("AMD", "C", 10, DateConstants.OCT_16_2020, 80, UsMoney.$(2));
    Portfolio portfolio = Portfolio.of(aaplPosition, amdPosition);
    PortfolioController portfolioController = new PortfolioController(portfolio, STUB_0_00_PRICER, dummyExpirationsFetcher);

    Collection<PositionView> positions = positionsFromViewModel(portfolioController);

    PositionView aaplPositionView = new PositionView(
        "AAPL", "C", "1", "2020-10-16", "125", "$6.00", "$600.00",
        "$0.00", "$0.00", "0", "0");
    PositionView amdPositionView = new PositionView(
        "AMD", "C", "10", "2020-10-16", "80", "$2.00", "$2,000.00",
        "$0.00", "$0.00", "0", "0"
    );
    assertThat(positions)
        .containsExactlyInAnyOrder(aaplPositionView, amdPositionView);
  }

  @Test
  public void submitOpenPositionThenViewShowsPosition() throws Exception {
    PortfolioController portfolioController = new PortfolioController(new Portfolio(), STUB_0_00_PRICER, dummyExpirationsFetcher);
    OpenPositionForm openPositionForm = new OpenPositionForm();
    openPositionForm.setUnderlyingSymbol("AMD");
    openPositionForm.setExpiration(DateConstants.OCT_16_2020);
    openPositionForm.setQuantity(10);
    openPositionForm.setOptionType("C");
    openPositionForm.setStrikePrice(80);
    openPositionForm.setUnitCost(BigDecimal.valueOf(2));
    portfolioController.handleOpenPosition(openPositionForm);

    Collection<PositionView> positionViews = positionsFromViewModel(portfolioController);

    PositionView amdPositionView = new PositionView(
        "AMD", "C", "10", "2020-10-16", "80", "$2.00", "$2,000.00",
        "$0.00", "$0.00", "0", "0"
    );

    assertThat(positionViews)
        .containsOnly(amdPositionView);
  }

  @Test
  public void currentValueOfPositionComesFromPricerPort() throws Exception {
    int quantity = 10;
    Position amdPosition = new Position("AMD", "C", quantity, DateConstants.OCT_16_2020, 80, UsMoney.$(2));
    Portfolio portfolio = Portfolio.of(amdPosition);
    int lastPriceForOption = 3;
    StubPricer stubPricer = new StubPricer(lastPriceForOption);
    PortfolioController portfolioController = new PortfolioController(portfolio, stubPricer, dummyExpirationsFetcher);

    Collection<PositionView> positionViews = positionsFromViewModel(portfolioController);

    assertThat(positionViews.stream().findFirst().get().getCurrentValue())
        .isEqualTo("$3,000.00"); // qty 10 * 100 per option * $3 last price
  }

  private Collection<PositionView> positionsFromViewModel(PortfolioController portfolioController) {
    Model model = new ConcurrentModel();
    portfolioController.viewPortfolio(model);

    return (Collection<PositionView>) model.getAttribute("positions");
  }

}

