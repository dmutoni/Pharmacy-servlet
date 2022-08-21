package com.example.pharmacyservlet.BaseClasses;

import com.example.pharmacyservlet.enums.UserRole;
import com.example.pharmacyservlet.models.User;

public abstract class UserMethods {
    private static UserRole userRole;
    public abstract void login(String username, String password);
    public abstract void register(User user);

    public static UserRole getUserRole() {
        return userRole;
    }

    public static void setUserRole(UserRole userRole) {
        UserMethods.userRole = userRole;
    }
}
