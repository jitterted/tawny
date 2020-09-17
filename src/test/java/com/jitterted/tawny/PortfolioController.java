package com.jitterted.tawny;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PortfolioController {

  @GetMapping("/view")
  public String viewPortfolio(Model model) {
    model.addAttribute("positions", List.of(""));
    return "view";
  }


}
