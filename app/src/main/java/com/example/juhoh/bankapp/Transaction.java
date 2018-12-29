package com.example.juhoh.bankapp;

public class Transaction {
    private String transactionType;
    private int amount;
    private String payer;
    private String payerAccount;
    private String payerCard;
    private String receiver;
    private String receiverAccount;

    // Values that are not defined every time are initialized as ""
    public Transaction() {
        payerCard = "";
        receiver = "";
        receiverAccount = "";
    }

    public Transaction setTransactionType(String transactionType) {
        this.transactionType = transactionType;
        return this;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public Transaction setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public int getAmount() {
        return amount;
    }

    public Transaction setPayer(String payer) {
        this.payer = payer;
        return this;
    }

    public String getPayer() {
        return payer;
    }

    public Transaction setPayerAccount(String payerAccount) {
        this.payerAccount = payerAccount;
        return this;
    }

    public String getPayerAccount() {
        return payerAccount;
    }

    public Transaction setPayerCard(String payerCard) {
        this.payerCard = payerCard;
        return this;
    }

    public String getPayerCard() {
        return payerCard;
    }

    public Transaction setReceiver(String receiver) {
        this.receiver = receiver;
        return this;
    }

    public String getReceiver() {
        return receiver;
    }

    public Transaction setReceiverAccount(String receiverAccount) {
        this.receiverAccount = receiverAccount;
        return this;
    }

    public String getReceiverAccount() {
        return receiverAccount;
    }
}
