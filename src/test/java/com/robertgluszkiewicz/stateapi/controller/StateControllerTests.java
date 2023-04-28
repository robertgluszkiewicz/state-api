package com.robertgluszkiewicz.stateapi.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.List;

import static org.mockito.Mockito.when;

import com.robertgluszkiewicz.stateapi.domain.ApiStateDto;
import com.robertgluszkiewicz.stateapi.service.StateService;

@SpringJUnitWebConfig
@WebMvcTest(StateController.class)
public class StateControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StateService stateService;

    @Test
    void shouldGetEmptyStates() throws Exception {
        when(stateService.getStatesByPopulation()).thenReturn(List.of());
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/v1/states")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldGetStates() throws Exception {
        List<ApiStateDto> apiStateDtoList =
                List.of(
                        new ApiStateDto("name1", 100, 2001),
                        new ApiStateDto("name2", 200, 2002),
                        new ApiStateDto("name3", 300, 2003)
                );

        when(stateService.getStatesByPopulation()).thenReturn(apiStateDtoList);
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/v1/states")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("name1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].population", Matchers.is(100)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].yearOfSourceData", Matchers.is(2001)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is("name2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].population", Matchers.is(200)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].yearOfSourceData", Matchers.is(2002)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].name", Matchers.is("name3")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].population", Matchers.is(300)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].yearOfSourceData", Matchers.is(2003)));
    }
}
