package com.robertgluszkiewicz.stateapi.dto;

public class ApiStateDto {
    private String name;
    private int population;
    private int yearOfSourceData;

    public ApiStateDto(String name, int population, int yearOfSourceData) {
        this.name = name;
        this.population = population;
        this.yearOfSourceData = yearOfSourceData;
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
        ApiStateDto that = (ApiStateDto) o;
        if (population != that.population) return false;
        if (yearOfSourceData != that.yearOfSourceData) return false;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + population;
        result = 31 * result + yearOfSourceData;
        return result;
    }

    @Override
    public String toString() {
        return "ApiStateDto{" +
                "name='" + name + '\'' +
                ", population=" + population +
                ", yearOfSourceData=" + yearOfSourceData +
                '}';
    }
}
