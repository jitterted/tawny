package com.jitterted.tawny.adapter.in.web;

import com.jitterted.tawny.domain.Portfolio;
import com.jitterted.tawny.domain.Position;
import com.jitterted.tawny.domain.Pricer;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    model.addAttribute("expirations", expirationsFetcher.fetchFor("AAPL"));
    return "open-position";
  }

  @PostMapping("/open-position")
  public String handleOpenPosition(@Valid OpenPositionForm openPositionForm) {
    Position position = OpenPositionForm.toPosition(openPositionForm);
    portfolio.add(position);
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
