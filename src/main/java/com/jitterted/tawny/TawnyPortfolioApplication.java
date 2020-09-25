package com.jitterted.tawny;

import com.jitterted.tawny.domain.Portfolio;
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

}
