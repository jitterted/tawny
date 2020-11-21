package com.jitterted.tawny.adapter.in.web;

import com.jitterted.tawny.domain.DateConstants;
import com.jitterted.tawny.domain.Portfolio;
import com.jitterted.tawny.domain.port.PortfolioRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class PortfolioControllerDropdownTest {

  @Test
  public void dropdownListIsPopulatedWithListOfExpirationDates() throws Exception {
    PortfolioRepository portfolioRepository = new FakePortfolioRepository(new Portfolio());
    PortfolioController portfolioController =
        new PortfolioController(portfolioRepository, new DummyPricer(),
                                new StubExpirationsFetcher(Collections.singletonList(DateConstants.OCT_16_2020)));

    List<LocalDate> expirations = portfolioController.expirationsFor("AMD");

    assertThat(expirations)
        .contains(DateConstants.OCT_16_2020);
  }

}