package com.jitterted.tawny.adapter.in.web;

import com.jitterted.tawny.domain.Position;
import org.joda.money.Money;
import org.joda.money.format.MoneyFormatter;
import org.joda.money.format.MoneyFormatterBuilder;

class PositionView {
  private final ContractView contract;
  private final String quantity;
  private final String unitCost; // TODO better term for this
  private final String totalCost;
  private final String currentOptionPrice;
  private final String currentValue;
  private final String valueGain;
  private final String valuePercentageGain;
  private final boolean rollDisabled;
  private final String id;

  private static final MoneyFormatter USD_FORMATTER =
      new MoneyFormatterBuilder().appendCurrencySymbolLocalized()
                                 .appendAmountLocalized()
                                 .toFormatter();


  public PositionView(String underlyingSymbol, String contractType, String expiration, String strikePrice,
                      String quantity, String unitCost, String totalCost, String currentOptionPrice,
                      String currentValue, String valueGain, String valuePercentageGain,
                      boolean rollDisabled, String id) {
    this.contract = new ContractView(underlyingSymbol, contractType, expiration, strikePrice);
    this.quantity = quantity;
    this.unitCost = unitCost;
    this.totalCost = totalCost;
    this.currentOptionPrice = currentOptionPrice;
    this.currentValue = currentValue;
    this.valueGain = valueGain;
    this.valuePercentageGain = valuePercentageGain;
    this.rollDisabled = rollDisabled;
    this.id = id;
  }

  public static PositionView fromDomain(Position position, Money lastPrice) {
    return new PositionView(position.contract().underlyingSymbol(),
                            position.contract().contractType(),
                            position.contract().expirationDate().toString(),
                            String.valueOf(position.contract().strikePrice()),
                            String.valueOf(position.quantity()),
                            USD_FORMATTER.print(position.unitCost()),
                            USD_FORMATTER.print(position.totalCost()),
                            USD_FORMATTER.print(lastPrice),
                            USD_FORMATTER.print(position.currentValue(lastPrice)),
                            "0",
                            "0",
                            position.isClosed(),
                            String.valueOf(position.getId()));
  }

  public String getQuantity() {
    return quantity;
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

  public boolean isRollDisabled() {
    return rollDisabled;
  }

  public String getId() {
    return id;
  }

  public ContractView getContract() {
    return contract;
  }

  @Override
  public String toString() {
    return "PositionView{" +
        "contract=" + contract +
        ", quantity='" + quantity + '\'' +
        ", unitCost='" + unitCost + '\'' +
        ", totalCost='" + totalCost + '\'' +
        ", currentOptionPrice='" + currentOptionPrice + '\'' +
        ", currentValue='" + currentValue + '\'' +
        ", valueGain='" + valueGain + '\'' +
        ", valuePercentageGain='" + valuePercentageGain + '\'' +
        ", rollDisabled=" + rollDisabled +
        ", id='" + id + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    PositionView that = (PositionView) o;

    if (rollDisabled != that.rollDisabled) return false;
    if (!contract.equals(that.contract)) return false;
    if (!quantity.equals(that.quantity)) return false;
    if (!unitCost.equals(that.unitCost)) return false;
    if (!totalCost.equals(that.totalCost)) return false;
    if (!currentOptionPrice.equals(that.currentOptionPrice)) return false;
    if (!currentValue.equals(that.currentValue)) return false;
    if (!valueGain.equals(that.valueGain)) return false;
    if (!valuePercentageGain.equals(that.valuePercentageGain)) return false;
    return id.equals(that.id);
  }

  @Override
  public int hashCode() {
    int result = contract.hashCode();
    result = 31 * result + quantity.hashCode();
    result = 31 * result + unitCost.hashCode();
    result = 31 * result + totalCost.hashCode();
    result = 31 * result + currentOptionPrice.hashCode();
    result = 31 * result + currentValue.hashCode();
    result = 31 * result + valueGain.hashCode();
    result = 31 * result + valuePercentageGain.hashCode();
    result = 31 * result + (rollDisabled ? 1 : 0);
    result = 31 * result + id.hashCode();
    return result;
  }
}
