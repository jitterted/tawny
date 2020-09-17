package com.jitterted.tawny;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collection;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest
public class PortfolioViewIntegrationTest {

  @Autowired
  MockMvc mockMvc;

  @Test
  public void givenPortfolioWithOpenPositionDisplaysPosition() throws Exception {
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/view"))
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
        .hasSize(1);

  }

}
