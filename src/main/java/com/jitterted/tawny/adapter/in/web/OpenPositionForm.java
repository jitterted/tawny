package com.jitterted.tawny.adapter.in.web;

import com.jitterted.tawny.domain.Position;
import org.jetbrains.annotations.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.time.OffsetDateTime;

public class OpenPositionForm {
  private String underlyingSymbol;

  @Pattern(regexp = "[CPcp]")
  private String optionType;

  @Min(1)
  private int quantity;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  @FutureOrPresent
  private OffsetDateTime expiration;

  @Min(1)
  private int strikePrice;

  private int unitCost;

  @NotNull
  static Position toPosition(OpenPositionForm openPositionForm) {
    return new Position(openPositionForm.getUnderlyingSymbol(),
                        openPositionForm.getOptionType(),
                        openPositionForm.getQuantity(),
                        openPositionForm.getExpiration(),
                        openPositionForm.getStrikePrice(),
                        openPositionForm.getUnitCost());
  }

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

  public OffsetDateTime getExpiration() {
    return expiration;
  }

  public void setExpiration(OffsetDateTime expiration) {
    this.expiration = expiration;
  }

  public int getStrikePrice() {
    return strikePrice;
  }

  public void setStrikePrice(int strikePrice) {
    this.strikePrice = strikePrice;
  }

  public int getUnitCost() {
    return unitCost;
  }

  public void setUnitCost(int unitCost) {
    this.unitCost = unitCost;
  }
}
