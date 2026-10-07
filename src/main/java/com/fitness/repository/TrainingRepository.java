package com.fitness.repository;

import com.fitness.model.Training;

import java.util.List;

public interface TrainingRepository {
    void save(Training training);
    Training findById(int id);
    List<Training> findAll();
}