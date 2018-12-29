package com.example.juhoh.bankapp;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class User {
    private int userId;
    private String name;
    private String city;
    private String zip;
    private String address;
    private String email;
    private String password;
    private ArrayList<Account> accountList = new ArrayList<Account>();

    public void addAccount(String accountNumber, String accountType) {
        Account account = new Account();
        account.setAccountNumber(accountNumber).setAccountType(accountType);
        accountList.add(account);
    }

    public void removeAccount(String accountNumber) {
        for(Account account : accountList) {
            if(account.getAccountNumber().equals(accountNumber)) {
                accountList.remove(account);
                break;
            }
        }
    }

    public User setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public int getUserId() {
        return userId;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setCity(String city) {
        this.city = city;
        return this;
    }

    public String getCity() {
        return city;
    }

    public User setZip(String zip) {
        this.zip = zip;
        return this;
    }

    public String getZip() {
        return zip;
    }

    public User setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Account> getAccountList() {
        return accountList;
    }

    @Override
    public String toString() {
        return name;
    }
}
