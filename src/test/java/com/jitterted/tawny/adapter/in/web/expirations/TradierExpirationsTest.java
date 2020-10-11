package com.jitterted.tawny.adapter.in.web.expirations;

import com.jitterted.tawny.TradierConfig;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Tag("integration")
class TradierExpirationsTest {

  @Autowired
  private TestRestTemplate testRestTemplate;

  @Autowired
  private TradierConfig tradierConfig;

  @Test
  public void expirationsForAmd() throws Exception {
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    headers.set("Authorization", "Bearer " + tradierConfig.getAccessToken());

    String url = "https://sandbox.tradier.com/v1/markets/options/expirations?symbol=AMD&includeAllRoots=true&strikes=true";
    HttpEntity<String> requestEntity = new HttpEntity<>(headers);

    ResponseEntity<TradierExpirations> expirationsResponse = testRestTemplate.exchange(
        url, HttpMethod.GET, requestEntity, TradierExpirations.class);

    TradierExpirations body = expirationsResponse.getBody();
    List<Expiration> expirations = body.getExpirations().getExpiration();

    List<LocalDate> dates = expirations.stream()
                                       .map(Expiration::getDate)
                                       .map(LocalDate::parse)
                                       .collect(Collectors.toList());

    assertThat(dates)
        .contains(LocalDate.of(2020, 10, 16), LocalDate.of(2020, 11, 20), LocalDate.of(2023, 1, 20));

    assertThat(expirations.get(0).getDate())
        .isEqualTo("2020-10-16");
    assertThat(expirations.get(0).getStrikes().getStrike())
        .usingElementComparator(BigDecimal::compareTo)
        .contains(BigDecimal.valueOf(45), BigDecimal.valueOf(50), BigDecimal.valueOf(74));

    System.out.println(expirations);
  }

}