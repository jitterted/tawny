package com.jitterted.tawny;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PortfolioController {

  private PositionView view = new PositionView("", "", "", "", "", "", "", "", "", "", "");

  public PortfolioController() {
  }

  public PortfolioController(PositionView view) {
    this.view = view;
  }

  @GetMapping("/view")
  public String viewPortfolio(Model model) {
    model.addAttribute("positions", List.of(view));
    return "view";
  }

  @GetMapping("/open-position")
  public String openPosition(Model model) {
    model.addAttribute("openPositionForm", new OpenPositionForm());
    return "open-position";
  }

  @PostMapping("/open-position")
  public String handleOpenPosition(OpenPositionForm openPositionForm) {
    view = PositionView.fromForm(openPositionForm);
    return "redirect:/view";
  }

}
