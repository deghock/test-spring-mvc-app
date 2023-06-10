package ru.amir.springcourse.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Amir
 */
public class City {

    private int id;

    @NotEmpty(message = "Name should not be empty")
    private String name;

    @Min(value = 0, message = "Population should be non-negative")
    private int population;

    private String code;

    public City() {

    }

    public City(int id, String name, int population, String code) {
        this.id = id;
        this.name = name;
        this.population = population;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
