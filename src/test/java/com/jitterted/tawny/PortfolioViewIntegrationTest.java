package com.jitterted.tawny;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collection;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest
public class PortfolioViewIntegrationTest {

  @Autowired
  MockMvc mockMvc;

  @Test
  public void givenPortfolioWithOpenPositionDisplaysPosition() throws Exception {
    MvcResult mvcResult = mockMvc.perform(get("/view"))
                                 .andExpect(status().isOk())
                                 .andExpect(view().name("view"))
                                 .andExpect(model().attributeExists("positions"))
                                 .andReturn();

    @SuppressWarnings("unchecked")
    Collection<String> positions = (Collection<String>)
        mvcResult.getModelAndView()
                 .getModel()
                 .get("positions");

    assertThat(positions)
        .isNotEmpty();
  }

  @Test
  public void openPositionPageShowsForm() throws Exception {
    MvcResult mvcResult = mockMvc.perform(get("/open-position"))
                                 .andExpect(status().isOk())
                                 .andExpect(view().name("open-position"))
                                 .andReturn();

    assertThat(mvcResult.getResponse().getContentAsString())
        .contains("<form");
  }

  @Test
  public void postFormToOpenPositionShowsInformationOnView() throws Exception {
    MultiValueMap<String, String> formParams = new LinkedMultiValueMap<>();
    formParams.add("underlyingSymbol", "AMD");
    formParams.add("type", "call");
    formParams.add("quantity", "1");
    formParams.add("expiration", "2020-09-20T10:00:00.000-04:00");
    formParams.add("strikePrice", "7500");
    formParams.add("unitCost", "570");

    mockMvc.perform(post("/open-position")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .params(formParams))
           .andExpect(redirectedUrl("/view"));

    MvcResult mvcResult = mockMvc.perform(get("/view"))
                                 .andReturn();

    assertThat(mvcResult.getResponse().getContentAsString())
        .contains("<td>AMD</td>", "7500", "570");
  }

}
