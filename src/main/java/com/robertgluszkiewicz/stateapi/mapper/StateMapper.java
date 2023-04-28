package com.robertgluszkiewicz.stateapi.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.robertgluszkiewicz.stateapi.domain.ApiStateDto;
import com.robertgluszkiewicz.stateapi.domain.DataUsaStateDto;
import com.robertgluszkiewicz.stateapi.domain.State;

@Service
public class StateMapper {
    public State mapToState(final DataUsaStateDto dataUsaStateDto) {
        return new State(
                dataUsaStateDto.getId(),
                dataUsaStateDto.getName(),
                dataUsaStateDto.getPopulation(),
                Integer.valueOf(dataUsaStateDto.getYear())
        );
    }

    public ApiStateDto mapToApiStateDto(final State state) {
        return new ApiStateDto(
                state.getName(),
                state.getPopulation(),
                state.getYearOfSourceData()
        );
    }

    public List<State> mapToStateList(final List<DataUsaStateDto> dataUsaStateDtoList) {
        return dataUsaStateDtoList.stream()
                .map(this::mapToState)
                .collect(Collectors.toList());
    }

    public List<ApiStateDto> mapToApiStateDtoList(final List<State> stateList) {
        return stateList.stream()
                .map(this::mapToApiStateDto)
                .collect(Collectors.toList());
    }
}
