package com.fitness.repository;

import com.fitness.model.Training;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTrainingRepository implements TrainingRepository {

    private final Map<Integer, Training> storage = new HashMap<>();

    @Override
    public void save(Training training) {
        storage.put(training.getId(), training);
    }

    @Override
    public Training findById(int id) {
        return storage.get(id);
    }

    @Override
    public List<Training> findAll() {
        return new ArrayList<>(storage.values());
    }
}