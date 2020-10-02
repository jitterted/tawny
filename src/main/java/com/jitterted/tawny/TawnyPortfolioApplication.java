package com.jitterted.tawny;

import com.jitterted.tawny.domain.Portfolio;
import com.jitterted.tawny.domain.Pricer;
import com.jitterted.tawny.domain.UsMoney;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TawnyPortfolioApplication {

  public static void main(String[] args) {
    SpringApplication.run(TawnyPortfolioApplication.class, args);
  }

  @Bean
  public Portfolio createPortfolio() {
    return new Portfolio();
  }

  public Pricer nullPricer() {
    return symbol -> UsMoney.zero();
  }

}
