package com.jitterted.tawny.adapter.in.web;

import com.jitterted.tawny.domain.Position;

import java.math.BigDecimal;

class PositionView {
  private final String underlyingSymbol;
  private final String optionType;
  private final String quantity;
  private final String expiration;
  private final String strikePrice;
  private final String unitCost; // TODO better term for this
  private final String totalCost;
  private final String currentOptionPrice;
  private final String currentValue;
  private final String valueGain;
  private final String valuePercentageGain;

  public PositionView(String underlyingSymbol, String optionType, String quantity, String expiration, String strikePrice, String unitCost, String totalCost, String currentOptionPrice, String currentValue, String valueGain, String valuePercentageGain) {
    this.underlyingSymbol = underlyingSymbol;
    this.optionType = optionType;
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

  public static PositionView fromDomain(Position position, BigDecimal lastPrice) {
    return new PositionView(position.underlyingSymbol(),
                            position.optionType(),
                            String.valueOf(position.quantity()),
                            position.expiration().toString(),
                            String.valueOf(position.strikePrice()),
                            String.valueOf(position.unitCost()),
                            String.valueOf(position.totalCost()),
                            lastPrice.toPlainString(),
                            position.currentValue(lastPrice).toPlainString(),
                            "0",
                            "0"
                            );
  }

  public String getUnderlyingSymbol() {
    return underlyingSymbol;
  }

  public String getOptionType() {
    return optionType;
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
        ", optionType='" + optionType + '\'' +
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
    if (!optionType.equals(that.optionType)) return false;
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
    result = 31 * result + optionType.hashCode();
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
