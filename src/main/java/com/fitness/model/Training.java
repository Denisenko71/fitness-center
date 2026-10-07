package com.fitness.model;

import java.util.ArrayList;
import java.util.List;

public class Training {
    private final int id;
    private final String name;
    private final Trainer trainer;
    private final int maxParticipants;
    private final List<Client> clients;

    public Training(int id, String name, Trainer trainer, int maxParticipants) {
        this.id = id;
        this.name = name;
        this.trainer = trainer;
        this.maxParticipants = maxParticipants;
        this.clients = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    @Override
    public String toString() {
        return "Training{id=" + id + ", name='" + name +
                "', trainer=" + trainer.getName() +
                ", clients=" + clients.size() + "/" + maxParticipants + "}";
    }
}