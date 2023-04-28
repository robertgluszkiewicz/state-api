package com.robertgluszkiewicz.stateapi.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.robertgluszkiewicz.stateapi.domain.ApiStateDto;
import com.robertgluszkiewicz.stateapi.service.StateService;

@RestController
@RequestMapping("/api/v1")
public class StateController {
    private final StateService stateService;

    @Autowired
    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    @GetMapping("/states")
    public ResponseEntity<List<ApiStateDto>> getStates() {
        return new ResponseEntity<>(stateService.getStatesByPopulation(), HttpStatus.OK);
    }
}
