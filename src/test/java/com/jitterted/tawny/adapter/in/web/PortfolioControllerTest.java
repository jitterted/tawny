package com.jitterted.tawny.adapter.in.web;

import com.jitterted.tawny.domain.DateConstants;
import com.jitterted.tawny.domain.Portfolio;
import com.jitterted.tawny.domain.Position;
import com.jitterted.tawny.domain.Pricer;
import com.jitterted.tawny.domain.UsMoney;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SuppressWarnings("unchecked")
class PortfolioControllerTest {

  private static final Pricer STUB_0_00_PRICER = symbol -> UsMoney.zero();

  private ExpirationsFetcher npeDummyExpirationsFetcher = null;

  @Test
  public void givenSingleOpenPositionViewReturnsPosition() throws Exception {
    Position aaplPosition = new Position("AAPL", "C", 1, DateConstants.OCT_16_2020, 125, UsMoney.$(6));
    Portfolio portfolio = Portfolio.of(aaplPosition);
    PortfolioController portfolioController = new PortfolioController(portfolio, STUB_0_00_PRICER, npeDummyExpirationsFetcher);

    Collection<PositionView> positions = positionsFromViewModel(portfolioController);

    PositionView expectedView = new PositionView(
        "AAPL", "C", "2020-10-16", "125", "1", "$6.00", "$600.00",
        "$0.00", "$0.00", "0", "0", false, "0");
    assertThat(positions)
        .contains(expectedView);
  }

  @Test
  public void givenSingleClosedPositionViewHasRollButtonDisabled() throws Exception {
    Position position = new Position("AAPL", "C", 1, DateConstants.OCT_16_2020, 125, UsMoney.$(6));
    position.close(UsMoney.$(8));
    Portfolio portfolio = Portfolio.of(position);
    PortfolioController portfolioController = new PortfolioController(portfolio, STUB_0_00_PRICER, npeDummyExpirationsFetcher);

    List<PositionView> positions = positionsFromViewModel(portfolioController);

    assertThat(positions.get(0).isRollDisabled())
        .isTrue();
  }

  @Test
  public void givenMultipleOpenPositionsViewReturnsAllPositions() throws Exception {
    Position aaplPosition = new Position("AAPL", "C", 1, DateConstants.OCT_16_2020, 125, UsMoney.$(6));
    Position amdPosition = new Position("AMD", "C", 10, DateConstants.OCT_16_2020, 80, UsMoney.$(2));
    Portfolio portfolio = Portfolio.of(aaplPosition, amdPosition);
    PortfolioController portfolioController = new PortfolioController(portfolio, STUB_0_00_PRICER, npeDummyExpirationsFetcher);

    Collection<PositionView> positions = positionsFromViewModel(portfolioController);

    PositionView aaplPositionView = new PositionView(
        "AAPL", "C", "2020-10-16", "125", "1", "$6.00", "$600.00",
        "$0.00", "$0.00", "0", "0", false, "0");
    PositionView amdPositionView = new PositionView(
        "AMD", "C", "2020-10-16", "80", "10", "$2.00", "$2,000.00",
        "$0.00", "$0.00", "0", "0",
        false, "1");
    assertThat(positions)
        .containsExactlyInAnyOrder(aaplPositionView, amdPositionView);
  }

  @Test
  public void submitOpenPositionThenViewShowsPosition() throws Exception {
    PortfolioController portfolioController = new PortfolioController(new Portfolio(), STUB_0_00_PRICER, npeDummyExpirationsFetcher);
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
        "AMD", "C", "2020-10-16", "80", "10", "$2.00", "$2,000.00",
        "$0.00", "$0.00", "0", "0",
        false, "0");

    assertThat(positionViews)
        .containsOnly(amdPositionView);
  }

  @Test
  public void currentValueOfPositionComesFromPricerPort() throws Exception {
    Portfolio portfolio = new Portfolio();
    int quantity = 10;
    portfolio.openPosition("AMD", "C", quantity, DateConstants.OCT_16_2020, 80, UsMoney.$(2));
    int lastPriceForOption = 3;
    StubPricer stubPricer = new StubPricer(lastPriceForOption);
    PortfolioController portfolioController = new PortfolioController(portfolio, stubPricer, npeDummyExpirationsFetcher);

    Collection<PositionView> positionViews = positionsFromViewModel(portfolioController);

    assertThat(positionViews.stream().findFirst().get().getCurrentValue())
        .isEqualTo("$3,000.00"); // qty 10 * 100 per option * $3 last price
  }

  private List<PositionView> positionsFromViewModel(PortfolioController portfolioController) {
    Model model = new ConcurrentModel();
    portfolioController.viewPortfolio(model);

    return (List<PositionView>) model.getAttribute("positions");
  }

  @Test
  public void rollPositionFormHasPositionToBeRolled() throws Exception {
    Portfolio portfolio = new Portfolio();
    Position amdPosition = portfolio.openPosition("AMD", "C", 1, DateConstants.OCT_16_2020, 80, UsMoney.$(2));

    PortfolioController portfolioController = new PortfolioController(
        portfolio, STUB_0_00_PRICER, new StubExpirationsFetcher(Collections.singletonList(DateConstants.OCT_16_2020)));

    Model model = new ConcurrentModel();
    portfolioController.rollPosition(model, "0");

    assertThat(model.getAttribute("contract"))
        .isNotNull();

    RollPositionForm rollPositionForm = extractRollPositionFormFrom(model);
    assertThat(rollPositionForm.getQuantity())
        .isEqualTo(amdPosition.quantity());
  }

  @NotNull
  private RollPositionForm extractRollPositionFormFrom(Model model) {
    Object form = model.getAttribute("rollPositionForm");
    assertThat(form)
        .isNotNull()
        .isInstanceOf(RollPositionForm.class);
    return (RollPositionForm)form;
  }

}

