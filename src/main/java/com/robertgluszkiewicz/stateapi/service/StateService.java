package com.robertgluszkiewicz.stateapi.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robertgluszkiewicz.stateapi.domain.ApiStateDto;
import com.robertgluszkiewicz.stateapi.domain.State;
import com.robertgluszkiewicz.stateapi.mapper.StateMapper;
import com.robertgluszkiewicz.stateapi.repository.StateRepository;

@Service
public class StateService {
    private static final int STATESLIMIT = 3;
    private final StateRepository stateRepository;
    private final StateMapper stateMapper;

    @Autowired
    public StateService(StateRepository stateRepository, StateMapper stateMapper) {
        this.stateRepository = stateRepository;
        this.stateMapper = stateMapper;
    }

    public List<ApiStateDto> getStatesByPopulation() {
                List<State> threeBiggestStates = stateRepository.getAllStates().stream()
                .sorted(Comparator.comparingInt(State::getPopulation).reversed())
                .limit(STATESLIMIT)
                .collect(Collectors.toList());
        List<ApiStateDto> apiStateDtoList = stateMapper.mapToApiStateDtoList(threeBiggestStates);
        return apiStateDtoList;
    }
}
