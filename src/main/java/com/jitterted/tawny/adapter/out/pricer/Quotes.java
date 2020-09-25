package com.jitterted.tawny.adapter.out.pricer;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.List;

public class Quotes {
  @JacksonXmlElementWrapper(useWrapping = false)
  private List<Quote> quote;

  public List<Quote> getQuote() {
    return quote;
  }

  public void setQuote(List<Quote> quote) {
    this.quote = quote;
  }
}

