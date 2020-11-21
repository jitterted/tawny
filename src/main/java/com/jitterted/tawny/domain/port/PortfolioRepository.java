package com.jitterted.tawny.domain.port;

import com.jitterted.tawny.domain.Portfolio;

import java.util.Optional;

public interface PortfolioRepository {
  Portfolio save(Portfolio portfolio);

  Optional<Portfolio> findById(PortfolioId portfolioId);
}
