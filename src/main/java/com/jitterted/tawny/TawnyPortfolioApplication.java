package com.jitterted.tawny;

import com.jitterted.tawny.domain.Portfolio;
import com.jitterted.tawny.domain.Pricer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class TawnyPortfolioApplication {

  public static void main(String[] args) {
    SpringApplication.run(TawnyPortfolioApplication.class, args);
  }

  @Bean
  public Portfolio createPortfolio() {
    return new Portfolio();
  }

  @Bean
  public Pricer nullPricer() {
    return symbol -> new BigDecimal("0.00");
  }

}
