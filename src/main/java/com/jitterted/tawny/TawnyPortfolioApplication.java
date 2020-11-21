package com.jitterted.tawny;

import com.jitterted.tawny.domain.Pricer;
import com.jitterted.tawny.domain.UsMoney;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TawnyPortfolioApplication {

  public static void main(String[] args) {
    SpringApplication.run(TawnyPortfolioApplication.class, args);
  }

  public Pricer nullPricer() {
    return symbol -> UsMoney.zero();
  }

}
