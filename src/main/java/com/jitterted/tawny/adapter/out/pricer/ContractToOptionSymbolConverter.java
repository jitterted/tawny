package com.jitterted.tawny.adapter.out.pricer;

import com.jitterted.tawny.domain.Contract;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ContractToOptionSymbolConverter {

  private static final DateTimeFormatter OPTION_DATE_FORMAT = DateTimeFormatter.ofPattern("yyMMdd");

  public String symbolFor(Contract contract) {
    return contract.underlyingSymbol()
        + formatDate(contract.expirationDate())
        + contract.contractType()
        + formatStrikePrice(contract);
  }

  @NotNull
  private String formatStrikePrice(Contract contract) {
    return String.format("%05d000", contract.strikePrice());
  }

  private String formatDate(LocalDate dateTime) {
    return dateTime.format(OPTION_DATE_FORMAT);
  }
}
