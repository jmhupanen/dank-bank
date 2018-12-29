package com.example.juhoh.bankapp;

public class Card {
    private String cardNumber;
    private String pinCode;
    private int paymentLimit;
    private int withdrawalLimit;

    public Card setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public Card setPinCode(String pinCode) {
        this.pinCode = pinCode;
        return this;
    }

    public String getPinCode() {
        return pinCode;
    }

    public Card setPaymentLimit(int depositLimit) {
        this.paymentLimit = depositLimit;
        return this;
    }

    public int getPaymentLimit() {
        return paymentLimit;
    }

    public Card setWithdrawalLimit(int withdrawalLimit) {
        this.withdrawalLimit = withdrawalLimit;
        return this;
    }

    public int getWithdrawalLimit() {
        return withdrawalLimit;
    }


    @Override
    public String toString() {
        return cardNumber;
    }
}
