package com.fitness.model;

public class Trainer {
    private final int id;
    private final String name;
    private final String specialization;

    public Trainer(int id, String name, String specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    @Override
    public String toString() {
        return "Trainer{id=" + id + ", name='" + name +
                "', specialization='" + specialization + "'}";
    }
}