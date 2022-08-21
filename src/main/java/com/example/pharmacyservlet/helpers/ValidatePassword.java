package com.example.pharmacyservlet.helpers;

public class ValidatePassword {
    private static final ValidatePassword instance = new ValidatePassword();
    public static ValidatePassword getInstance() {
        return instance;
    }

    public Boolean adminPassword(String password) {
        return password.length() == 8;
    }
    public Boolean patientPassword(String password) {
        return password.length() == 7;
    }
    public Boolean physicianPassword(String password) {
        return password.length() == 6;
    }

    public Boolean pharmacistPassword(String password) {
        return password.length() == 5;
    }
}
