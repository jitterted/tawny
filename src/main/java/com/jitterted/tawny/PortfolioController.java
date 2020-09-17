package com.jitterted.tawny;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PortfolioController {

  @GetMapping("/view")
  public String viewPortfolio(Model model) {
    model.addAttribute("positions",
                       List.of(new PositionView(
                           "AAPL", "C", "1", "Oct 16 2020", "125.00", "6.40", "640.00",
                           "6.35", "635.00", "($5.00)", "(8%)")));
    return "view";
  }

  @GetMapping("/open-position")
  public String openPosition() {
    return "open-position";
  }

}
