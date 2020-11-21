package com.jitterted.tawny.adapter.in.web;

import com.jitterted.tawny.domain.Portfolio;
import com.jitterted.tawny.domain.port.PortfolioId;
import com.jitterted.tawny.domain.port.PortfolioRepository;

import java.util.Optional;

public class FakePortfolioRepository implements PortfolioRepository {
  private Portfolio portfolio;

  public FakePortfolioRepository() {
  }

  public FakePortfolioRepository(Portfolio portfolio) {
    save(portfolio);
  }

  @Override
  public Portfolio save(Portfolio portfolio) {
    portfolio.setId(new PortfolioId(0L));
    this.portfolio = portfolio;
    return portfolio;
  }

  @Override
  public Optional<Portfolio> findById(PortfolioId portfolioId) {
    return Optional.of(portfolio);
  }
}
