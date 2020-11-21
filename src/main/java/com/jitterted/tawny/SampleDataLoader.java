package com.jitterted.tawny;

import com.jitterted.tawny.domain.Portfolio;
import com.jitterted.tawny.domain.UsMoney;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class SampleDataLoader implements ApplicationRunner {

  private final Portfolio portfolio;

  @Autowired
  public SampleDataLoader(Portfolio portfolio) {
    this.portfolio = portfolio;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    portfolio.openPosition("AMD", "C", 10, LocalDate.of(2020, 10, 30), 75, UsMoney.$(9));
  }
}
