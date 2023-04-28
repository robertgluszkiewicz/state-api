package com.robertgluszkiewicz.stateapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

@SpringJUnitWebConfig
@WebMvcTest(StateController.class)
public class StateControllerTests {
    @Test
    void shouldGetStates() throws Exception {
    }
}
