package com.robertgluszkiewicz.stateapi.repository;

import java.util.List;

import com.robertgluszkiewicz.stateapi.domain.State;

public interface StateRepository {
    List<State> getAllStates();
}
