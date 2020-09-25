package com.jitterted.tawny.adapter.out.pricer;

import java.math.BigDecimal;

public class Quote {
  private String symbol;

  private String description;

  private String open_interest;

  private String ask_date;

  private String type;

  private String expiration_date;

  private String high;

  private String last_volume;

  private String change_percentage;

  private String low;

  private String bidexch;

  private String average_volume;

  private String askexch;

  private String close;

  private String bid_date;

  private String week_52_high;

  private BigDecimal last;

  private String option_type;

  private String change;

  private String strike;

  private String prevclose;

  private String week_52_low;

  private String asksize;

  private String underlying;

  private String trade_date;

  private String volume;

  private String expiration_type;

  private String exch;

  private String ask;

  private String bidsize;

  private String root_symbol;

  private String root_symbols;

  private String contract_size;

  private String bid;

  private String open;

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getOpen_interest() {
    return open_interest;
  }

  public void setOpen_interest(String open_interest) {
    this.open_interest = open_interest;
  }

  public String getAsk_date() {
    return ask_date;
  }

  public void setAsk_date(String ask_date) {
    this.ask_date = ask_date;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getExpiration_date() {
    return expiration_date;
  }

  public void setExpiration_date(String expiration_date) {
    this.expiration_date = expiration_date;
  }

  public String getHigh() {
    return high;
  }

  public void setHigh(String high) {
    this.high = high;
  }

  public String getLast_volume() {
    return last_volume;
  }

  public void setLast_volume(String last_volume) {
    this.last_volume = last_volume;
  }

  public String getChange_percentage() {
    return change_percentage;
  }

  public void setChange_percentage(String change_percentage) {
    this.change_percentage = change_percentage;
  }

  public String getLow() {
    return low;
  }

  public void setLow(String low) {
    this.low = low;
  }

  public String getBidexch() {
    return bidexch;
  }

  public void setBidexch(String bidexch) {
    this.bidexch = bidexch;
  }

  public String getAverage_volume() {
    return average_volume;
  }

  public void setAverage_volume(String average_volume) {
    this.average_volume = average_volume;
  }

  public String getAskexch() {
    return askexch;
  }

  public void setAskexch(String askexch) {
    this.askexch = askexch;
  }

  public String getClose() {
    return close;
  }

  public void setClose(String close) {
    this.close = close;
  }

  public String getBid_date() {
    return bid_date;
  }

  public void setBid_date(String bid_date) {
    this.bid_date = bid_date;
  }

  public String getWeek_52_high() {
    return week_52_high;
  }

  public void setWeek_52_high(String week_52_high) {
    this.week_52_high = week_52_high;
  }

  public BigDecimal getLast() {
    return last;
  }

  public void setLast(BigDecimal last) {
    this.last = last;
  }

  public String getOption_type() {
    return option_type;
  }

  public void setOption_type(String option_type) {
    this.option_type = option_type;
  }

  public String getChange() {
    return change;
  }

  public void setChange(String change) {
    this.change = change;
  }

  public String getStrike() {
    return strike;
  }

  public void setStrike(String strike) {
    this.strike = strike;
  }

  public String getPrevclose() {
    return prevclose;
  }

  public void setPrevclose(String prevclose) {
    this.prevclose = prevclose;
  }

  public String getWeek_52_low() {
    return week_52_low;
  }

  public void setWeek_52_low(String week_52_low) {
    this.week_52_low = week_52_low;
  }

  public String getAsksize() {
    return asksize;
  }

  public void setAsksize(String asksize) {
    this.asksize = asksize;
  }

  public String getUnderlying() {
    return underlying;
  }

  public void setUnderlying(String underlying) {
    this.underlying = underlying;
  }

  public String getTrade_date() {
    return trade_date;
  }

  public void setTrade_date(String trade_date) {
    this.trade_date = trade_date;
  }

  public String getVolume() {
    return volume;
  }

  public void setVolume(String volume) {
    this.volume = volume;
  }

  public String getExpiration_type() {
    return expiration_type;
  }

  public void setExpiration_type(String expiration_type) {
    this.expiration_type = expiration_type;
  }

  public String getExch() {
    return exch;
  }

  public void setExch(String exch) {
    this.exch = exch;
  }

  public String getAsk() {
    return ask;
  }

  public void setAsk(String ask) {
    this.ask = ask;
  }

  public String getBidsize() {
    return bidsize;
  }

  public void setBidsize(String bidsize) {
    this.bidsize = bidsize;
  }

  public String getRoot_symbols() {
    return root_symbols;
  }

  public void setRoot_symbols(String root_symbols) {
    this.root_symbols = root_symbols;
  }

  public String getRoot_symbol() {
    return root_symbol;
  }

  public void setRoot_symbol(String root_symbol) {
    this.root_symbol = root_symbol;
  }

  public String getContract_size() {
    return contract_size;
  }

  public void setContract_size(String contract_size) {
    this.contract_size = contract_size;
  }

  public String getBid() {
    return bid;
  }

  public void setBid(String bid) {
    this.bid = bid;
  }

  public String getOpen() {
    return open;
  }

  public void setOpen(String open) {
    this.open = open;
  }

  @Override
  public String toString() {
    return "Quote [symbol = " + symbol + ", description = " + description + ", open_interest = " + open_interest + ", ask_date = " + ask_date + ", type = " + type + ", expiration_date = " + expiration_date + ", high = " + high + ", last_volume = " + last_volume + ", change_percentage = " + change_percentage + ", low = " + low + ", bidexch = " + bidexch + ", average_volume = " + average_volume + ", askexch = " + askexch + ", close = " + close + ", bid_date = " + bid_date + ", week_52_high = " + week_52_high + ", last = " + last + ", option_type = " + option_type + ", change = " + change + ", strike = " + strike + ", prevclose = " + prevclose + ", week_52_low = " + week_52_low + ", asksize = " + asksize + ", underlying = " + underlying + ", trade_date = " + trade_date + ", volume = " + volume + ", expiration_type = " + expiration_type + ", exch = " + exch + ", ask = " + ask + ", bidsize = " + bidsize + ", root_symbols = " + root_symbols + ", root_symbol = " + root_symbol + ", contract_size = " + contract_size + ", bid = " + bid + ", open = " + open + "]";
  }
}
