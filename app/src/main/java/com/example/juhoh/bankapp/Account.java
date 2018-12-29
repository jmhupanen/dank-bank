package com.example.juhoh.bankapp;

import java.util.ArrayList;

public class Account {
    private String accountNumber;
    private String accountType;
    private boolean isFrozen;
    private int balance;
    private ArrayList<Card> cardList = new ArrayList<Card>();

    public Account() {
        balance = 0;
        isFrozen = false;
    }

    public void addCard(String cardNumber, String pinCode, int paymentLimit, int withdrawalLimit) {
        Card card = new Card();
        card.setCardNumber(cardNumber).setPinCode(pinCode).setPaymentLimit(paymentLimit).setWithdrawalLimit(withdrawalLimit);
        cardList.add(card);
    }

    public void removeCard(String cardNumber) {
        for(Card card : cardList) {
            if(card.getCardNumber().equals(cardNumber)) {
                cardList.remove(card);
                break;
            }
        }
    }

    public void addMoney(int amount) {
        balance += amount;
    }

    public void reduceMoney(int amount) {
        balance -= amount;
    }

    public Account setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Account setAccountType(String accountType) {
        this.accountType = accountType;
        return this;
    }

    public String getAccountType() {
        return accountType;
    }

    public Account freezeAccount() {
        isFrozen = true;
        return this;
    }

    public boolean isFrozen() {
        return isFrozen;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public ArrayList<Card> getCardList() {
        return cardList;
    }

    @Override
    public String toString() {
        return accountNumber;
    }
}
