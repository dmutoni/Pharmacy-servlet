package com.example.pharmacyservlet.services;

import com.example.pharmacyservlet.BaseClasses.UserMethods;
import com.example.pharmacyservlet.database.TemporaryDatabase;
import com.example.pharmacyservlet.enums.UserRole;
import com.example.pharmacyservlet.models.User;

import java.util.LinkedHashMap;
import java.util.Random;

public class AdminService extends UserMethods {
    @Override
    public void login(String username, String password){
        System.out.println("Admin Signed in");
        UserMethods.setUserRole(UserRole.admin);
    }

    @Override
    public void register(User admin) {
//        LinkedHashMap<Integer, User> admins  = TemporaryDatabase.getInstance().getUsers();
        TemporaryDatabase temporaryDatabase = new TemporaryDatabase();
        LinkedHashMap<Integer, User> admins = new LinkedHashMap<>();
        admins.put(new Random().nextInt(23), admin);
//        admins.put(new Random().nextInt(23), admin);
    }
}
