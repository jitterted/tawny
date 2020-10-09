package com.jitterted.tawny.adapter.in.web;

import java.time.LocalDate;
import java.util.List;

public class StubExpirationsFetcher implements ExpirationsFetcher {

  private List<LocalDate> expirationDates;

  public StubExpirationsFetcher(List<LocalDate> expirationDates) {
    this.expirationDates = expirationDates;
  }

  @Override
  public List<LocalDate> fetchFor(String symbol) {
    return expirationDates;
  }
}
