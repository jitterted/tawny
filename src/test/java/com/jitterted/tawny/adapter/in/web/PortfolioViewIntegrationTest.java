package com.jitterted.tawny.adapter.in.web;

import com.jitterted.tawny.domain.Portfolio;
import com.jitterted.tawny.domain.Position;
import com.jitterted.tawny.domain.Pricer;
import com.jitterted.tawny.domain.UsMoney;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
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

  @Test
  public void rollPositionPageShowsForm() throws Exception {
    Position position = new Portfolio().openPosition("", "", 0, LocalDate.now(), 0, UsMoney.zero());
    given(portfolio.findById(anyLong())).willReturn(Optional.of(position));

    MvcResult mvcResult = mockMvc.perform(get("/roll-position/" + position.getId()))
                                 .andExpect(status().isOk())
                                 .andExpect(view().name("roll-position"))
                                 .andExpect(model().attributeExists("rollPositionForm"))
                                 .andReturn();

    assertThat(mvcResult.getResponse().getContentAsString())
        .contains("<form");
  }

}
