package com.jitterted.tawny.adapter.in.web;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;

public class OpenPositionForm {
  private String underlyingSymbol;

  @Pattern(regexp = "[CPcp]")
  private String optionType;

  private int quantity;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  @FutureOrPresent
  private LocalDate expiration;

  @Min(1)
  private int strikePrice;

  private BigDecimal unitCost;

  public String getUnderlyingSymbol() {
    return underlyingSymbol;
  }

  public void setUnderlyingSymbol(String underlyingSymbol) {
    this.underlyingSymbol = underlyingSymbol;
  }

  public String getOptionType() {
    return optionType;
  }

  public void setOptionType(String optionType) {
    this.optionType = optionType;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public LocalDate getExpiration() {
    return expiration;
  }

  public void setExpiration(LocalDate expiration) {
    this.expiration = expiration;
  }

  public int getStrikePrice() {
    return strikePrice;
  }

  public void setStrikePrice(int strikePrice) {
    this.strikePrice = strikePrice;
  }

  public BigDecimal getUnitCost() {
    return unitCost;
  }

  public void setUnitCost(BigDecimal unitCost) {
    this.unitCost = unitCost;
  }
}
