package com.jitterted.tawny.adapter.in.web;

import com.jitterted.tawny.domain.Contract;

public class ContractView {
  private final String underlyingSymbol;
  private final String contractType;
  private final String expiration;
  private final String strikePrice;

  public ContractView(String underlyingSymbol, String contractType, String expiration, String strikePrice) {
    this.underlyingSymbol = underlyingSymbol;
    this.contractType = contractType;
    this.expiration = expiration;
    this.strikePrice = strikePrice;
  }

  public static ContractView from(Contract contract) {
    return new ContractView(contract.underlyingSymbol(),
                            contract.contractType(),
                            contract.expirationDate().toString(),
                            String.valueOf(contract.strikePrice()));
  }

  public String getUnderlyingSymbol() {
    return underlyingSymbol;
  }

  public String getContractType() {
    return contractType;
  }

  public String getExpiration() {
    return expiration;
  }

  public String getStrikePrice() {
    return strikePrice;
  }

  @Override
  public String toString() {
    return "ContractView{" +
        "underlyingSymbol='" + underlyingSymbol + '\'' +
        ", contractType='" + contractType + '\'' +
        ", expiration='" + expiration + '\'' +
        ", strikePrice='" + strikePrice + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContractView that = (ContractView) o;

    if (!underlyingSymbol.equals(that.underlyingSymbol)) return false;
    if (!contractType.equals(that.contractType)) return false;
    if (!expiration.equals(that.expiration)) return false;
    return strikePrice.equals(that.strikePrice);
  }

  @Override
  public int hashCode() {
    int result = underlyingSymbol.hashCode();
    result = 31 * result + contractType.hashCode();
    result = 31 * result + expiration.hashCode();
    result = 31 * result + strikePrice.hashCode();
    return result;
  }
}
