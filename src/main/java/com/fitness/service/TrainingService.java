package com.fitness.service;

import com.fitness.model.Client;
import com.fitness.model.Training;
import com.fitness.repository.TrainingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrainingService {
    private static final Logger logger = LoggerFactory.getLogger(TrainingService.class);
    private final TrainingRepository repository;
    public TrainingService(TrainingRepository repository) {
        this.repository = repository;
    }

    public void enrollClient(int trainingId, Client client) {
        logger.info("Попытка записи клиента {} на тренировку {}", client.getName(), trainingId);
        Training training = repository.findById(trainingId);
        if (training == null) {
            logger.error("Ошибка записи: тренировка с id={} не найдена", trainingId);
            throw new IllegalArgumentException(
                    "Тренировка с id=" + trainingId + " не найдена");
        }

        if (training.getClients().size() >= training.getMaxParticipants()) {
            logger.warn("Не удалось записать клиента {}: на тренировке {} нет свободных мест",
                    client.getName(), trainingId);
            throw new IllegalStateException(
                    "Нельзя записать: достигнут лимит участников (" +
                            training.getMaxParticipants() + ")");
        }

        boolean alreadyEnrolled = training.getClients().stream()
                .anyMatch(c -> c.getId() == client.getId());
        if (alreadyEnrolled) {
            logger.warn("Клиент {} уже записан на тренировку {}", client.getName(), trainingId);
            throw new IllegalStateException(
                    "Клиент уже записан на эту тренировку");
        }

        training.addClient(client);
        repository.save(training);
        logger.info("Клиент {} успешно записан на тренировку {}", client.getName(), trainingId);
    }

    public Training getTraining(int id) {
        return repository.findById(id);
    }
}

