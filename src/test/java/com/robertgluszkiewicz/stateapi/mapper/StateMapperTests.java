package com.robertgluszkiewicz.stateapi.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.robertgluszkiewicz.stateapi.dto.ApiStateDto;
import com.robertgluszkiewicz.stateapi.dto.DataUsaStateDto;
import com.robertgluszkiewicz.stateapi.domain.State;

public class StateMapperTests {
    StateMapper stateMapper = new StateMapper();
    State state1 = new State("id1", "name1", 1, 2001);
    State state2 = new State("id2", "name2", 2, 2002);
    DataUsaStateDto dataUsaStateDto1 = new DataUsaStateDto(
            "id1", "name1", "2001", 1);
    DataUsaStateDto dataUsaStateDto2 = new DataUsaStateDto(
            "id2", "name2", "2002", 2);
    ApiStateDto apiStateDto1 = new ApiStateDto(
            "name1", 1, 2001);
    ApiStateDto apiStateDto2 = new ApiStateDto(
            "name2", 2, 2002);
    List<State> stateList = List.of(state1, state2);
    List<DataUsaStateDto> dataUsaStateDtoList = List.of(dataUsaStateDto1, dataUsaStateDto2);
    List<ApiStateDto> apiStateDtoList = List.of(apiStateDto1, apiStateDto2);

    @Test
    public void shouldMapToState() {
        State result = stateMapper.mapToState(dataUsaStateDto1);
        assertEquals(state1.getId(), result.getId());
        assertEquals(state1.getName(), result.getName());
        assertEquals(state1.getPopulation(), result.getPopulation());
        assertEquals(state1.getYearOfSourceData(), result.getYearOfSourceData());
    }

    @Test
    public void shouldMapToApiStateDto() {
        ApiStateDto result = stateMapper.mapToApiStateDto(state1);
        assertEquals(apiStateDto1.getName(), result.getName());
        assertEquals(apiStateDto1.getPopulation(), result.getPopulation());
        assertEquals(apiStateDto1.getYearOfSourceData(), result.getYearOfSourceData());
    }

    @Test
    public void shouldMapToStateList() {
        List<State> result = stateMapper.mapToStateList(dataUsaStateDtoList);
        assertEquals(stateList.size(), result.size());
        assertEquals(stateList.get(0).getId(), result.get(0).getId());
        assertEquals(stateList.get(1), result.get(1));
    }

    @Test
    public void shouldMapToApiStateDtoList() {
        List<ApiStateDto> result = stateMapper.mapToApiStateDtoList(stateList);
        assertEquals(apiStateDtoList.size(), result.size());
        assertEquals(apiStateDtoList.get(0).getPopulation(), result.get(0).getPopulation());
        assertEquals(apiStateDtoList.get(1), result.get(1));
    }
}
