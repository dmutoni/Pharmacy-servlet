package com.example.pharmacyservlet.database;

import com.example.pharmacyservlet.models.User;

import java.util.LinkedHashMap;

public class TemporaryDatabase {
    private final LinkedHashMap<Integer, User> users = new LinkedHashMap<>();

    private static final TemporaryDatabase instance = new TemporaryDatabase();
    public TemporaryDatabase() {

    }
    public static TemporaryDatabase getInstance() {
        return instance;
    }

    public LinkedHashMap<Integer, User> getUsers() {
        return users;
    }
}
