package com.jitterted.tawny;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PortfolioController {

  private List<PositionView> views = new ArrayList<>();

  public PortfolioController() {
  }

  public PortfolioController(List<PositionView> views) {
    this.views = views;
  }

  @GetMapping("/view")
  public String viewPortfolio(Model model) {
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
    views.add(PositionView.fromForm(openPositionForm));
    return "redirect:/view";
  }

}
