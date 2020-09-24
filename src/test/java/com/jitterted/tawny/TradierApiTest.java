package com.jitterted.tawny;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class TradierApiTest {

  RestTemplate restTemplate = new RestTemplate();

  @Test
  public void requestOptionQuoteReturnsPrice() throws Exception {
    HttpHeaders headers = new HttpHeaders();
//    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    headers.set("Authorization", "Bearer " + "Lk8GquRZ1i5n2MwAsYCAo2g1HSfR");
    String url = "https://sandbox.tradier.com/v1/markets/quotes?symbols=AMD201016C00075000,AAPL";
    HttpEntity<String> requestEntity = new HttpEntity<>(headers);

    ResponseEntity<String> quoteResponse = restTemplate.exchange(
        url, HttpMethod.GET, requestEntity, String.class);

    assertThat(quoteResponse.getStatusCode())
        .isEqualByComparingTo(HttpStatus.OK);

    String body = quoteResponse.getBody();
    System.out.println(body);
    assertThat(body)
        .contains("AMD");
  }

}
