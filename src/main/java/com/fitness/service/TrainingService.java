package com.fitness.service;

import com.fitness.model.Client;
import com.fitness.model.Training;
import com.fitness.repository.TrainingRepository;

public class TrainingService {

    private final TrainingRepository repository;

    public TrainingService(TrainingRepository repository) {
        this.repository = repository;
    }

    public void enrollClient(int trainingId, Client client) {
        Training training = repository.findById(trainingId);

        if (training == null) {
            throw new IllegalArgumentException(
                    "Тренировка с id=" + trainingId + " не найдена");
        }

        if (training.getClients().size() >= training.getMaxParticipants()) {
            throw new IllegalStateException(
                    "Нельзя записать: достигнут лимит участников (" +
                            training.getMaxParticipants() + ")");
        }

        boolean alreadyEnrolled = training.getClients().stream()
                .anyMatch(c -> c.getId() == client.getId());
        if (alreadyEnrolled) {
            throw new IllegalStateException(
                    "Клиент уже записан на эту тренировку");
        }

        training.addClient(client);
        repository.save(training);
    }

    public Training getTraining(int id) {
        return repository.findById(id);
    }
}