package com.example.juhoh.bankapp;

import java.util.ArrayList;

public class TransactionList {
    private ArrayList<Transaction> transactions = new ArrayList<Transaction>();

    private static final TransactionList instance = new TransactionList();

    private TransactionList(){
    }

    public static TransactionList getInstance() {
        return instance;
    }

    public void addDepWit(String type,String payer, String account, String card, int amount){
        Transaction transaction = new Transaction();
        transaction.setTransactionType(type).setPayer(payer).setPayerAccount(account).setPayerCard(card).setAmount(amount);
        transactions.add(transaction);
    }

    public void addTransfer(String payer, String payerAccount, String receiver, String receiverAccount, int amount) {
        Transaction transaction = new Transaction();
        transaction.setTransactionType("Transfer").setPayer(payer).setPayerAccount(payerAccount).setReceiver(receiver).setReceiverAccount(receiverAccount).setAmount(amount);
        transactions.add(transaction);
    }

    public void addPayment(String payer, String account, String receiver, String card, int amount) {
        Transaction transaction = new Transaction();
        transaction.setTransactionType("Payment").setPayer(payer).setPayerAccount(account).setReceiver(receiver).setPayerCard(card).setAmount(amount);
        transactions.add(transaction);
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
}
