package com.jitterted.tawny.adapter.in.web;

import com.jitterted.tawny.domain.Portfolio;
import com.jitterted.tawny.domain.Position;
import com.jitterted.tawny.domain.Pricer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PortfolioController {

  private final Portfolio portfolio;
  private final Pricer pricer;

  @Autowired
  public PortfolioController(Portfolio portfolio, Pricer pricer) {
    this.portfolio = portfolio;
    this.pricer = pricer;
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
    return "open-position";
  }

  @PostMapping("/open-position")
  public String handleOpenPosition(OpenPositionForm openPositionForm) {
    Position position = OpenPositionForm.toPosition(openPositionForm);
    portfolio.add(position);
    return "redirect:/view";
  }

  private PositionView enrichWithLastPrice(Position position) {
    BigDecimal lastPrice = pricer.fetchPriceQuote(position.underlyingSymbol());
    return PositionView.fromDomain(position, lastPrice);
  }
}
