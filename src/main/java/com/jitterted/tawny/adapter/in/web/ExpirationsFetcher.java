package com.jitterted.tawny.adapter.in.web;

import java.time.LocalDate;
import java.util.List;

// PORT definition for external Option Expiration retrieval
public interface ExpirationsFetcher {
  List<LocalDate> fetchFor(String symbol);
}
