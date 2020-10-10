package com.jitterted.tawny.adapter.in.web;

import com.jitterted.tawny.TradierConfig;
import com.jitterted.tawny.domain.DateConstants;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collection;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@SpringBootTest
@AutoConfigureMockMvc
@Tag("integration")
public class PortfolioViewE2eTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  private TradierConfig tradierConfig;

  @Test
  public void tradierConfig() throws Exception {
    assertThat(tradierConfig.getAccessToken())
        .isNotBlank();
  }

  @Test
  public void postFormToOpenPositionShowsInformationOnView() throws Exception {
    MultiValueMap<String, String> formParams = new LinkedMultiValueMap<>();
    formParams.add("underlyingSymbol", "AMD");
    formParams.add("optionType", "C"); // radio button choice
    formParams.add("quantity", "10");
    formParams.add("expiration", DateConstants.OCT_16_2020.toString());
    formParams.add("strikePrice", "75");
    formParams.add("unitCost", "5");

    mockMvc.perform(post("/open-position")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .params(formParams))
           .andExpect(redirectedUrl("/view"));

    MvcResult mvcResult = mockMvc.perform(get("/view"))
                                 .andReturn();

    Collection<String> positions = (Collection<String>)
        mvcResult.getModelAndView()
                 .getModel()
                 .get("positions");

    assertThat(positions)
        .hasSize(1);

    assertThat(mvcResult.getResponse().getContentAsString())
        .contains("<td>AMD</td>", "75", "$5,000.00");
  }
}
