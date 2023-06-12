package com.robertgluszkiewicz.stateapi.dto;

public class DataUsaStateDto {
    private String id;
    private String name;
    private String year;
    private int population;

    public DataUsaStateDto(String id, String name, String year, int population) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.population = population;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getYear() {
        return year;
    }

    public int getPopulation() {
        return population;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataUsaStateDto that = (DataUsaStateDto) o;
        if (population != that.population) return false;
        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        return year.equals(that.year);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + year.hashCode();
        result = 31 * result + population;
        return result;
    }

    @Override
    public String toString() {
        return "DataUsaStateDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", year='" + year + '\'' +
                ", population=" + population +
                '}';
    }
}
