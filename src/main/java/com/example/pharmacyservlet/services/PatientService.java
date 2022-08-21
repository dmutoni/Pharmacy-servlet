package com.example.pharmacyservlet.services;

import com.example.pharmacyservlet.BaseClasses.UserMethods;
import com.example.pharmacyservlet.database.TemporaryDatabase;
import com.example.pharmacyservlet.enums.UserRole;
import com.example.pharmacyservlet.models.User;

import java.util.LinkedHashMap;
import java.util.Random;

public class PatientService extends UserMethods {
    @Override
    public void login(String username, String password){
        System.out.println("Patient Signed in");
        UserMethods.setUserRole(UserRole.patient);
    }

    @Override
    public void register(User patient) {
        LinkedHashMap<Integer, User> patients  = TemporaryDatabase.getInstance().getUsers();
        patients.put(new Random().nextInt(23), patient);
    }
}
