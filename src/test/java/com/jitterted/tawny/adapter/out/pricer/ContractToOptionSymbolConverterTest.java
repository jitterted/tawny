package com.jitterted.tawny.adapter.out.pricer;

import com.jitterted.tawny.domain.Contract;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.assertj.core.api.Assertions.*;

class ContractToOptionSymbolConverterTest {

  private static final OffsetDateTime OCT_16_2020 = OffsetDateTime.of(2020, 10, 16, 16, 0, 0, 0, ZoneOffset.of("-04:00"));

  @Test
  public void contractWith2DigitStrikePriceIsConvertedToStandardSymbologyString() throws Exception {
    Contract contract = new Contract("AMD", "C", OCT_16_2020, 75);

    String symbol = new ContractToOptionSymbolConverter().symbolFor(contract);

    assertThat(symbol)
        .isEqualTo("AMD201016C00075000");
  }

  @Test
  public void contractWith4DigitIntegralStrikePriceIsConverted() throws Exception {
    Contract contract = new Contract("AMZN", "C", OCT_16_2020, 2990);

    String symbol = new ContractToOptionSymbolConverter().symbolFor(contract);

    assertThat(symbol)
        .isEqualTo("AMZN201016C02990000");
  }

  @Disabled("Need a decimal format for Strike Price to test this out")
  @Test
  public void contractWithDecimalStrikePriceIsConverted() throws Exception {
//    Contract contract = new Contract("AAPL", "P", OCT_16_2020, 112.25)
  }
}