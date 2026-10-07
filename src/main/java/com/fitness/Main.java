package com.fitness;

import com.fitness.model.Client;
import com.fitness.model.Trainer;
import com.fitness.model.Training;
import com.fitness.repository.InMemoryTrainingRepository;
import com.fitness.repository.TrainingRepository;
import com.fitness.service.TrainingService;

public class Main {
    public static void main(String[] args) {
        String environment     = System.getProperty("app.environment",     "не задано");
        String maxParticipants = System.getProperty("app.maxParticipants", "не задано");
        String debugMode       = System.getProperty("app.debugMode",       "не задано");

        System.out.println("Конфигурация приложения");
        System.out.println("Environment     : " + environment);
        System.out.println("Max participants: " + maxParticipants);
        System.out.println("Debug mode      : " + debugMode);
        System.out.println();

        TrainingRepository repository = new InMemoryTrainingRepository();
        TrainingService service = new TrainingService(repository);

        Trainer trainer = new Trainer(1, "Денисенко Надежда", "Йога");
        Training yoga = new Training(100, "Утренняя йога", trainer, 2);
        repository.save(yoga);

        Client anna  = new Client(1, "Анна");
        Client boris = new Client(2, "Борис");
        Client viktor = new Client(3, "Виктор");

        service.enrollClient(100, anna);
        service.enrollClient(100, boris);
        System.out.println("После двух записей: " + service.getTraining(100));

        try {
            service.enrollClient(100, viktor);
        } catch (IllegalStateException e) {
            System.out.println("Ожидаемая ошибка: " + e.getMessage());
        }

        System.out.println("Итог: " + service.getTraining(100));
    }
}