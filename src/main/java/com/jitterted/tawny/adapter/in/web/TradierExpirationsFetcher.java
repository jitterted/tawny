package com.jitterted.tawny.adapter.in.web;

import com.jitterted.tawny.TradierConfig;
import com.jitterted.tawny.adapter.in.web.expirations.Expiration;
import com.jitterted.tawny.adapter.in.web.expirations.TradierExpirations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TradierExpirationsFetcher implements ExpirationsFetcher {
  private final RestTemplate restTemplate = new RestTemplate();

  private final TradierConfig tradierConfig;

  @Autowired
  public TradierExpirationsFetcher(TradierConfig tradierConfig) {
    this.tradierConfig = tradierConfig;
  }

  @Override
  public List<LocalDate> fetchFor(String symbol) {
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    headers.set("Authorization", "Bearer " + tradierConfig.getAccessToken());

    String url = "https://sandbox.tradier.com/v1/markets/options/expirations?symbol=AMD&includeAllRoots=true&strikes=true";
    HttpEntity<String> requestEntity = new HttpEntity<>(headers);

    ResponseEntity<TradierExpirations> expirationsResponse = restTemplate.exchange(
        url, HttpMethod.GET, requestEntity, TradierExpirations.class);

    TradierExpirations body = expirationsResponse.getBody();
    List<Expiration> expirations = body.getExpirations().getExpiration();


    List<LocalDate> dates = expirations.stream()
                                       .map(Expiration::getDate)
                                       .map(LocalDate::parse)
                                       .collect(Collectors.toList());

    return dates;
  }
}
