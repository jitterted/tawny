package com.jitterted.tawny.adapter.in.web;

import com.jitterted.tawny.domain.Position;
import org.joda.money.Money;
import org.joda.money.format.MoneyFormatter;
import org.joda.money.format.MoneyFormatterBuilder;

class PositionView {
  private final String underlyingSymbol;
  private final String contractType;
  private final String quantity;
  private final String expiration;
  private final String strikePrice;
  private final String unitCost; // TODO better term for this
  private final String totalCost;
  private final String currentOptionPrice;
  private final String currentValue;
  private final String valueGain;
  private final String valuePercentageGain;

  private static final MoneyFormatter USD_FORMATTER =
      new MoneyFormatterBuilder().appendCurrencySymbolLocalized()
                                 .appendAmountLocalized()
                                 .toFormatter();


  public PositionView(String underlyingSymbol, String contractType, String quantity, String expiration, String strikePrice, String unitCost, String totalCost, String currentOptionPrice, String currentValue, String valueGain, String valuePercentageGain) {
    this.underlyingSymbol = underlyingSymbol;
    this.contractType = contractType;
    this.quantity = quantity;
    this.expiration = expiration;
    this.strikePrice = strikePrice;
    this.unitCost = unitCost;
    this.totalCost = totalCost;
    this.currentOptionPrice = currentOptionPrice;
    this.currentValue = currentValue;
    this.valueGain = valueGain;
    this.valuePercentageGain = valuePercentageGain;
  }

  public static PositionView fromDomain(Position position, Money lastPrice) {
    return new PositionView(position.contract().underlyingSymbol(),
                            position.contract().contractType(),
                            String.valueOf(position.quantity()),
                            position.contract().expirationDate().toString(),
                            String.valueOf(position.contract().strikePrice()),
                            USD_FORMATTER.print(position.unitCost()),
                            USD_FORMATTER.print(position.totalCost()),
                            USD_FORMATTER.print(lastPrice),
                            USD_FORMATTER.print(position.currentValue(lastPrice)),
                            "0",
                            "0"
    );
  }

  public String getUnderlyingSymbol() {
    return underlyingSymbol;
  }

  public String getContractType() {
    return contractType;
  }

  public String getQuantity() {
    return quantity;
  }

  public String getExpiration() {
    return expiration;
  }

  public String getStrikePrice() {
    return strikePrice;
  }

  public String getUnitCost() {
    return unitCost;
  }

  public String getTotalCost() {
    return totalCost;
  }

  public String getCurrentOptionPrice() {
    return currentOptionPrice;
  }

  public String getCurrentValue() {
    return currentValue;
  }

  public String getValueGain() {
    return valueGain;
  }

  public String getValuePercentageGain() {
    return valuePercentageGain;
  }

  @Override
  public String toString() {
    return "PositionView{" +
        "underlyingSymbol='" + underlyingSymbol + '\'' +
        ", optionType='" + contractType + '\'' +
        ", quantity='" + quantity + '\'' +
        ", expiration='" + expiration + '\'' +
        ", strikePrice='" + strikePrice + '\'' +
        ", unitCost='" + unitCost + '\'' +
        ", totalCost='" + totalCost + '\'' +
        ", currentOptionPrice='" + currentOptionPrice + '\'' +
        ", currentTotalValue='" + currentValue + '\'' +
        ", valueGain='" + valueGain + '\'' +
        ", valuePercentageGain='" + valuePercentageGain + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    PositionView that = (PositionView) o;

    if (!underlyingSymbol.equals(that.underlyingSymbol)) return false;
    if (!contractType.equals(that.contractType)) return false;
    if (!quantity.equals(that.quantity)) return false;
    if (!expiration.equals(that.expiration)) return false;
    if (!strikePrice.equals(that.strikePrice)) return false;
    if (!unitCost.equals(that.unitCost)) return false;
    if (!totalCost.equals(that.totalCost)) return false;
    if (!currentOptionPrice.equals(that.currentOptionPrice)) return false;
    if (!currentValue.equals(that.currentValue)) return false;
    if (!valueGain.equals(that.valueGain)) return false;
    return valuePercentageGain.equals(that.valuePercentageGain);
  }

  @Override
  public int hashCode() {
    int result = underlyingSymbol.hashCode();
    result = 31 * result + contractType.hashCode();
    result = 31 * result + quantity.hashCode();
    result = 31 * result + expiration.hashCode();
    result = 31 * result + strikePrice.hashCode();
    result = 31 * result + unitCost.hashCode();
    result = 31 * result + totalCost.hashCode();
    result = 31 * result + currentOptionPrice.hashCode();
    result = 31 * result + currentValue.hashCode();
    result = 31 * result + valueGain.hashCode();
    result = 31 * result + valuePercentageGain.hashCode();
    return result;
  }
}
