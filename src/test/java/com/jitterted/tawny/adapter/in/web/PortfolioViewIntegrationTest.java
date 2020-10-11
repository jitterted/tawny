package com.jitterted.tawny.adapter.in.web;

import com.jitterted.tawny.domain.Portfolio;
import com.jitterted.tawny.domain.Pricer;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collection;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SuppressWarnings("unchecked")
@WebMvcTest
@Tag("integration")
public class PortfolioViewIntegrationTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  Portfolio portfolio;

  @MockBean
  Pricer pricer;

  @MockBean
  ExpirationsFetcher expirationsFetcher;

  @Test
  public void givenNewPortfolioViewModelContainsEmptyPositionList() throws Exception {
    MvcResult mvcResult = mockMvc.perform(get("/view"))
                                 .andExpect(status().isOk())
                                 .andExpect(view().name("view"))
                                 .andExpect(model().attributeExists("positions"))
                                 .andReturn();

    Collection<String> positions = (Collection<String>)
        mvcResult.getModelAndView()
                 .getModel()
                 .get("positions");

    assertThat(positions)
        .isEmpty();
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


}
