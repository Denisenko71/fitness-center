package com.fitness.service;

import com.fitness.model.Client;
import com.fitness.model.Trainer;
import com.fitness.model.Training;
import com.fitness.repository.InMemoryTrainingRepository;
import com.fitness.repository.TrainingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrainingServiceTest {

    private TrainingRepository repository;
    private TrainingService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryTrainingRepository();
        service = new TrainingService(repository);

        Trainer trainer = new Trainer(1, "Денисенко Надежда", "Йога");

        Training yoga = new Training(100, "Утренняя йога", trainer, 2);
        repository.save(yoga);

        Training personal = new Training(200, "Персональная тренировка", trainer, 1);
        repository.save(personal);
    }

    @Test
    void test1_clientSuccessfullyEnrolled() {
        Client anna = new Client(1, "Анна");

        service.enrollClient(100, anna);

        Training result = service.getTraining(100);
        assertEquals(1, result.getClients().size(),
                "После записи одного клиента в списке должен быть 1 человек");
        assertEquals("Анна", result.getClients().get(0).getName(),
                "Записан должен быть именно клиент Анна");
    }

    @Test
    void test2_clientCannotEnrollTwice() {
        Client anna = new Client(1, "Анна");

        service.enrollClient(100, anna);

        IllegalStateException ex = assertThrows(
                IllegalStateException.class,
                () -> service.enrollClient(100, anna)
        );

        assertTrue(ex.getMessage().contains("уже записан"),
                "Сообщение должно говорить, что клиент уже записан");
        assertEquals(999, service.getTraining(100).getClients().size(),
                "Клиент не должен дублироваться в списке");
    }

    @Test
    void test3_clientCannotEnrollIfPlaceTakenByAnother() {
        Client anna = new Client(1, "Анна");
        Client boris = new Client(2, "Борис");

        service.enrollClient(200, anna);

        IllegalStateException ex = assertThrows(
                IllegalStateException.class,
                () -> service.enrollClient(200, boris)
        );

        assertTrue(ex.getMessage().contains("лимит"),
                "Сообщение должно упоминать лимит участников");
        assertEquals(1, service.getTraining(200).getClients().size(),
                "Борис не должен быть записан, место занято Анной");
        assertEquals("Анна", service.getTraining(200).getClients().get(0).getName(),
                "В списке должна остаться только Анна");
    }

    @Test
    void test4_enrollClientToNonExistentTraining() {
        Client anna = new Client(1, "Анна");

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> service.enrollClient(999, anna)
        );

        assertTrue(ex.getMessage().contains("не найдена")
                        || ex.getMessage().contains("не существует"),
                "Сообщение должно говорить, что тренировка не найдена");
    }
}