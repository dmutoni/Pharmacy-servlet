package com.example.pharmacyservlet.services;


import com.example.pharmacyservlet.BaseClasses.UserMethods;
import com.example.pharmacyservlet.database.TemporaryDatabase;
import com.example.pharmacyservlet.enums.UserRole;
import com.example.pharmacyservlet.models.User;

import java.util.LinkedHashMap;
import java.util.Random;

public class PharmacistService extends UserMethods {
    @Override
    public void login(String username, String password){
        System.out.println("Pharmacist Signed in");
        UserMethods.setUserRole(UserRole.pharmacist);
    }

    @Override
    public void register(User pharmacist) {
        LinkedHashMap<Integer, User> pharmacists  = TemporaryDatabase.getInstance().getUsers();
        pharmacists.put(new Random().nextInt(23), pharmacist);
    }
}

