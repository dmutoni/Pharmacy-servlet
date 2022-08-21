package com.example.pharmacyservlet.controllers;

import com.example.pharmacyservlet.enums.UserRole;
import com.example.pharmacyservlet.helpers.ValidatePassword;
import com.example.pharmacyservlet.models.User;
import com.example.pharmacyservlet.services.AdminService;
import com.example.pharmacyservlet.services.PatientService;
import com.example.pharmacyservlet.services.PharmacistService;
import com.example.pharmacyservlet.services.PhysicianService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@WebServlet("/signup")
public class Register extends HttpServlet {
    PrintWriter printWriter;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.addHeader("Access-Control-Allow-Origin", "*");
        printWriter = res.getWriter();
        try {
            AdminService admin = new AdminService();
            PatientService patient = new PatientService();
            PharmacistService pharmacist = new PharmacistService();
            PhysicianService physician = new PhysicianService();
            LinkedHashMap<Integer, User> lhmUsers = new LinkedHashMap<>();

            String jsonString = req.getReader().lines().collect(Collectors.joining());

            User myObject = new Gson().fromJson(jsonString, User.class);
            System.out.println(myObject.getUserRole());
            System.out.println(myObject.getAge());

            String successMessage = null;
            if(!Objects.equals(myObject.getPassword(), myObject.getRetype_password())){
                successMessage= "Passwords do not match";
            }

            switch (myObject.getUserRole()) {
                case "admin":
                    System.out.println(myObject.getPassword());
                    if (ValidatePassword.getInstance().adminPassword(myObject.getPassword())) {
                        admin.register(myObject);
                        successMessage = "Admin account is created successfully";
                    } else {
                        successMessage = "Password should be 8 numbers";
                    }
                    break;
                case "patient":
                    System.out.println("Patient account..");
                    if (!ValidatePassword.getInstance().patientPassword(String.valueOf(myObject.getPassword()))) {
                        patient.register(myObject);
                        successMessage = "Patient account is created successfully";
                    } else {
                        successMessage = "Password should be only 7 numbers";

                    }
                    break;
                case "physician":

                    if (!ValidatePassword.getInstance().physicianPassword(String.valueOf(myObject.getPassword()))) {
                        physician.register(myObject);
                        successMessage = "Physician account is created successfully";
                    } else {
                        successMessage = "Password should be only 6 numbers";
                    }

                    break;
                case "pharmacist":

                    if (!ValidatePassword.getInstance().pharmacistPassword(String.valueOf(myObject.getPassword()))) {
                        pharmacist.register(myObject);
                        successMessage = "Pharmacist account is successfully";
                    } else {
                        successMessage = "Password should be only 5 numbers";
                    }
                    break;
            }

            printWriter.print(successMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        printWriter = response.getWriter();

        LinkedHashMap<Integer, User> mappedUsers = new LinkedHashMap<>();
    }
}
