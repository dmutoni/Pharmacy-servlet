package com.example.pharmacyservlet.services;

import com.example.pharmacyservlet.BaseClasses.UserMethods;
import com.example.pharmacyservlet.database.TemporaryDatabase;
import com.example.pharmacyservlet.enums.UserRole;
import com.example.pharmacyservlet.models.User;

import java.util.LinkedHashMap;
import java.util.Random;

public class PhysicianService extends UserMethods {
    @Override
    public void login(String username, String password){
        System.out.println("Physician Signed in");
        UserMethods.setUserRole(UserRole.physician);
    }

    @Override
    public void register(User physician) {
        LinkedHashMap<Integer, User> physicians  = TemporaryDatabase.getInstance().getUsers();
        physicians.put(new Random().nextInt(23), physician);
    }
}
