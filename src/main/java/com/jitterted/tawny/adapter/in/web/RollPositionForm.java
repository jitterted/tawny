package com.jitterted.tawny.adapter.in.web;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDate;

public class RollPositionForm {
  @Min(1)
  private int quantity;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  @FutureOrPresent
  private LocalDate expiration;

  @Min(1)
  private int strikePrice;

  private BigDecimal closeCost;

  private BigDecimal openCost;

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

  public BigDecimal getCloseCost() {
    return closeCost;
  }

  public void setCloseCost(BigDecimal closeCost) {
    this.closeCost = closeCost;
  }

  public BigDecimal getOpenCost() {
    return openCost;
  }

  public void setOpenCost(BigDecimal openCost) {
    this.openCost = openCost;
  }
}
