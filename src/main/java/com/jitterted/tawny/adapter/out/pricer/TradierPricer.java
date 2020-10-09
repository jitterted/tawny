package com.jitterted.tawny.adapter.out.pricer;

import com.jitterted.tawny.TradierConfig;
import com.jitterted.tawny.domain.Contract;
import com.jitterted.tawny.domain.Pricer;
import com.jitterted.tawny.domain.UsMoney;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Service
public class TradierPricer implements Pricer {

  private final RestTemplate restTemplate = new RestTemplate();
  private final TradierConfig tradierConfig;

  @Autowired
  public TradierPricer(TradierConfig tradierConfig) {
    this.tradierConfig = tradierConfig;
  }

  @Override
  public Money fetchPriceQuote(Contract contract) {
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));
    headers.set("Authorization", "Bearer " + tradierConfig.getAccessToken());

    String optionSymbol = new ContractToOptionSymbolConverter().symbolFor(contract);
    String url = "https://sandbox.tradier.com/v1/markets/quotes?symbols=" + optionSymbol;

    HttpEntity<String> requestEntity = new HttpEntity<>(headers);
    ResponseEntity<Quotes> quotesResponse = restTemplate.exchange(
        url, HttpMethod.GET, requestEntity, Quotes.class);

    List<Quote> quotes = quotesResponse.getBody().getQuote();
    if (quotes == null) {
      return UsMoney.zero();
    }

    BigDecimal lastPrice = quotes.get(0).getLast();
    return UsMoney.$(lastPrice);
  }
}
