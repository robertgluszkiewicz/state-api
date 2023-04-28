package com.robertgluszkiewicz.stateapi.domain;

public class State {
    private String id;
    private String name;
    private int population;
    private int yearOfSourceData;

    public State(String id, String name, int population, int yearOfSourceData) {
        this.id = id;
        this.name = name;
        this.population = population;
        this.yearOfSourceData = yearOfSourceData;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public int getYearOfSourceData() {
        return yearOfSourceData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        if (population != state.population) return false;
        if (yearOfSourceData != state.yearOfSourceData) return false;
        if (!id.equals(state.id)) return false;
        return name.equals(state.name);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + population;
        result = 31 * result + yearOfSourceData;
        return result;
    }

    @Override
    public String toString() {
        return "State{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", population=" + population +
                ", yearOfSourceData=" + yearOfSourceData +
                '}';
    }
}


