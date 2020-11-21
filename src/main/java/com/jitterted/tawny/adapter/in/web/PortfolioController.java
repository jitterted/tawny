package com.jitterted.tawny.adapter.in.web;

import com.jitterted.tawny.domain.Portfolio;
import com.jitterted.tawny.domain.Position;
import com.jitterted.tawny.domain.Pricer;
import com.jitterted.tawny.domain.UsMoney;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PortfolioController {

  private final Portfolio portfolio;
  private final Pricer pricer;
  private final ExpirationsFetcher expirationsFetcher;

  @Autowired
  public PortfolioController(Portfolio portfolio, Pricer pricer, ExpirationsFetcher expirationsFetcher) {
    this.portfolio = portfolio;
    this.pricer = pricer;
    this.expirationsFetcher = expirationsFetcher;
  }

  @GetMapping("/view")
  public String viewPortfolio(Model model) {
    List<PositionView> views = portfolio.stream()
                                        .map(this::enrichWithLastPrice)
                                        .collect(Collectors.toList());
    model.addAttribute("positions", views);
    return "view";
  }

  @GetMapping("/open-position")
  public String openPosition(Model model) {
    model.addAttribute("openPositionForm", new OpenPositionForm());
    addExpirationsTo(model);
    return "open-position";
  }

  private void addExpirationsTo(Model model) {
    model.addAttribute("expirations", expirationsFetcher.fetchFor("AAPL"));
  }

  @PostMapping("/open-position")
  public String handleOpenPosition(@Valid OpenPositionForm openPositionForm) {
    portfolio.openPosition(
        openPositionForm.getUnderlyingSymbol(),
        openPositionForm.getOptionType(),
        openPositionForm.getQuantity(),
        openPositionForm.getExpiration(),
        openPositionForm.getStrikePrice(),
        UsMoney.$(openPositionForm.getUnitCost()));
    return "redirect:/view";
  }

  @GetMapping("/roll-position/{id}")
  public String rollPosition(Model model, @PathVariable String id) {
    Position position = portfolio.findById(Long.parseLong(id)).orElseThrow();
    model.addAttribute("contract", ContractView.from(position.contract()));
    model.addAttribute("rollPositionForm", RollPositionForm.from(position));
    addExpirationsTo(model);
    return "roll-position";
  }

  @PostMapping("/roll-position")
  public String handleRollPosition(@Valid RollPositionForm rollPositionForm) {
    Position position = portfolio.findById(rollPositionForm.getId()).orElseThrow();
    portfolio.roll(position,
                   UsMoney.$(rollPositionForm.getCloseCost()),
                   rollPositionForm.getQuantity(),
                   rollPositionForm.getExpiration(),
                   rollPositionForm.getStrikePrice(),
                   UsMoney.$(rollPositionForm.getOpenCost()));
    return "redirect:/view";
  }

  private PositionView enrichWithLastPrice(Position position) {
    Money lastPrice = pricer.fetchPriceQuote(position.contract());
    return PositionView.fromDomain(position, lastPrice);
  }

  public List<LocalDate> expirationsFor(String symbol) {
    return expirationsFetcher.fetchFor(symbol);
  }
}
