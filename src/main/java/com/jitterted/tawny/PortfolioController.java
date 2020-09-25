package com.jitterted.tawny;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
public class PortfolioController {

  private List<Position> positions = new ArrayList<>();

  public PortfolioController() {
  }

  public PortfolioController(Position... positions) {
    this.positions = Arrays.asList(positions);
  }

  @GetMapping("/view")
  public String viewPortfolio(Model model) {
    List<PositionView> views = positions.stream()
                                        .map(enrichWithLastPrice())
                                        .collect(Collectors.toList());
    model.addAttribute("positions", views);
    return "view";
  }

  @NotNull
  private Function<Position, PositionView> enrichWithLastPrice() {
    return PositionView::fromDomain;
  }

  @GetMapping("/open-position")
  public String openPosition(Model model) {
    model.addAttribute("openPositionForm", new OpenPositionForm());
    return "open-position";
  }

  @PostMapping("/open-position")
  public String handleOpenPosition(OpenPositionForm openPositionForm) {
    Position position = OpenPositionForm.toPosition(openPositionForm);
    positions.add(position);
    return "redirect:/view";
  }

}
