package com.jitterted.tawny.adapter;

import com.jitterted.tawny.domain.Portfolio;
import com.jitterted.tawny.domain.port.PortfolioId;
import com.jitterted.tawny.domain.port.PortfolioRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryPortfolioRepository implements PortfolioRepository {
  private final Map<PortfolioId, Portfolio> portfolioMap = new HashMap<>();
  private final AtomicLong sequence = new AtomicLong();

  @Override
  public Portfolio save(Portfolio portfolio) {
    if (portfolio.getId() == null) {
      portfolio.setId(new PortfolioId(sequence.getAndIncrement()));
    }
    portfolioMap.put(portfolio.getId(), portfolio);
    return portfolio;
  }

  @Override
  public Optional<Portfolio> findById(PortfolioId portfolioId) {
    return Optional.ofNullable(portfolioMap.get(portfolioId));
  }
}
