package com.jitterted.tawny.adapter.out.pricer;

import com.jitterted.tawny.domain.Contract;
import com.jitterted.tawny.domain.NewYorkTimeConstants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

// random_port thingy here is required to autowire a TestRestTemplate
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TradierApiTest {

  @Autowired
  private TestRestTemplate testRestTemplate;

  @Test
  public void requestOptionQuoteViaXmlApiReturnsQuote() throws Exception {
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));
    headers.set("Authorization", "Bearer " + "Lk8GquRZ1i5n2MwAsYCAo2g1HSfR");
    String optionSymbol = new ContractToOptionSymbolConverter().symbolFor(
        new Contract("AMD", "C", NewYorkTimeConstants.OCT_16_2020, 75)
    );
    String url = "https://sandbox.tradier.com/v1/markets/quotes?symbols=" + optionSymbol + ",AAPL,AMD";
    HttpEntity<String> requestEntity = new HttpEntity<>(headers);

    ResponseEntity<Quotes> quotesResponse = testRestTemplate.exchange(
        url, HttpMethod.GET, requestEntity, Quotes.class);

    assertThat(quotesResponse.getStatusCode())
        .isEqualByComparingTo(HttpStatus.OK);

    List<Quote> quotes = quotesResponse.getBody().getQuote();

    assertThat(quotes)
        .hasSize(3);

    assertThat(quotes)
        .extracting(Quote::getSymbol)
        .contains(optionSymbol);

    quotes.stream().forEach(quote -> System.out.println(quote.getSymbol() + " = " + quote.getLast()));
  }

}
