package com.jitterted.tawny.adapter.out.pricer;

import com.jitterted.tawny.domain.Contract;
import com.jitterted.tawny.domain.DateConstants;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ContractToOptionSymbolConverterTest {

  @Test
  public void contractWith2DigitStrikePriceIsConvertedToStandardSymbologyString() throws Exception {
    Contract contract = new Contract("AMD", "C", DateConstants.OCT_16_2020, 75);

    String symbol = new ContractToOptionSymbolConverter().symbolFor(contract);

    assertThat(symbol)
        .isEqualTo("AMD201016C00075000");
  }

  @Test
  public void contractWith4DigitIntegralStrikePriceIsConverted() throws Exception {
    Contract contract = new Contract("AMZN", "C", DateConstants.OCT_16_2020, 2990);

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