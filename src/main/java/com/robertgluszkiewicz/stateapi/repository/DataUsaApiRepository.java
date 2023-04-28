package com.robertgluszkiewicz.stateapi.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.robertgluszkiewicz.stateapi.client.DataUsaApiClient;
import com.robertgluszkiewicz.stateapi.domain.State;
import com.robertgluszkiewicz.stateapi.mapper.StateMapper;

@Repository
public class DataUsaApiRepository implements StateRepository{
    private final DataUsaApiClient dataUsaApiClient;
    private final StateMapper stateMapper;

    @Autowired
    public DataUsaApiRepository(DataUsaApiClient dataUsaApiClient, StateMapper stateMapper) {
        this.dataUsaApiClient = dataUsaApiClient;
        this.stateMapper = stateMapper;
    }

    @Override
    public List<State> getAllStates() {
        return stateMapper.mapToStateList(dataUsaApiClient.getStates());
    }
}
