package com.example.pharmacyservlet.controllers;

import com.example.pharmacyservlet.BaseClasses.UserMethods;
import com.example.pharmacyservlet.database.TemporaryDatabase;
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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@WebServlet("/login")
public class Authenticate extends HttpServlet {
    protected void processRequest() {
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        processRequest();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response)
            throws IOException {
        processRequest();

        response.addHeader("Access-Control-Allow-Origin", "*");
        String requestData = req.getReader().lines().collect(Collectors.joining());
        User jsonData = new Gson().fromJson(requestData, User.class);

        LinkedHashMap<Integer, User> mappedUsers;
        mappedUsers = TemporaryDatabase.getInstance().getUsers();
        UserMethods.setUserRole(null);

        boolean userFound = false;
        for (User user : usersList(mappedUsers)) {
            if (user.getUsername().equals(jsonData.getUsername())
                    && user.getPassword().equals(jsonData.getPassword())){
                userFound = true;
                handleLogin(user.getUserRole().toLowerCase(), user.getUsername(),
                        String.valueOf(user.getPassword()));
            }
        }

        authResponse(response, userFound);
    }

    private ArrayList<User> usersList(LinkedHashMap<Integer, User> mappedUsers) {
        ArrayList<User> usersList = new ArrayList<>();

        for (Map.Entry<Integer, User> entry : mappedUsers.entrySet()) {
            User umData = entry.getValue();
            usersList.add(umData);
        }
        return usersList;
    }

    private void authResponse(HttpServletResponse response, boolean userFound) {
        PrintWriter out;
        try {
            out = response.getWriter();
            if (!userFound) {
                out.print("Invalid credentials");
            } else {
                out.print(UserMethods.getUserRole());
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
            //out.print("something went wrong");

        }

    }

    private void handleLogin(String role, String username, String password) {
        switch (role) {
            case "admin" -> {
                AdminService admin = new AdminService();
                admin.login(username, password);
            }
            case "patient" -> {
                PatientService patient = new PatientService();
                patient.login(username, password);
            }
            case "pharmacist" -> {
                PharmacistService pharmacist = new PharmacistService();
                pharmacist.login(username, password);
            }
            case "physician" -> {
                PhysicianService physician = new PhysicianService();
                physician.login(username, password);
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
