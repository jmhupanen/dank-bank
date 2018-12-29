package com.example.juhoh.bankapp;

import java.util.ArrayList;

public class Bank {
    private User currentUser;
    private int currentUserId;
    private ArrayList<User> userList = new ArrayList<User>();

    private static final Bank instance = new Bank();

    // Admin user details are set in Bank's constructor
    private Bank() {
        currentUserId = -1;
        User user = new User();
        user.setUserId(0).setName("admin").setCity("Mordor").setZip("66666").setAddress("4 Priver Drive").setEmail("admin@bank.com").setPassword("1234");
        userList.add(user);
    }

    public static Bank getInstance() {
        return instance;
    }

    public void addUser(String name, String city, String zip, String address, String email, String password) {
        User user = new User();
        user.setUserId(userList.get(userList.size()-1).getUserId()+1).setName(name).setCity(city).setZip(zip).setAddress(address).setEmail(email).setPassword(password);
        userList.add(user);
    }

    public void removeUser(int userId) {
        for(User user : userList) {
            if(user.getUserId() == userId) {
                userList.remove(user);
                break;
            }
        }
    }

    public void addAccount(String accountNumber, String accountType) {
        currentUser.addAccount(accountNumber, accountType);
    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }

    public User getCurrentUser() {
        if(currentUserId == -1) {
            User user = new User();
            user.setName("");
            user.setEmail("");
            return user;
        }
        else {
            return currentUser;
        }
    }

    public void setCurrentUserId(int userId) {
        currentUserId = userId;
    }

    public int getCurrentUserId() {
        return currentUserId;
    }

    public ArrayList<User> getUserlist() {
        return userList;
    }
}
