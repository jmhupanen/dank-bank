package com.example.juhoh.bankapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.juhoh.bankapp.Bank;
import com.example.juhoh.bankapp.R;
import com.example.juhoh.bankapp.Transaction;
import com.example.juhoh.bankapp.TransactionList;


/**
 * A simple {@link Fragment} subclass.
 */
public class History extends Fragment {
    TextView historyMsg;
    private Bank bank = Bank.getInstance();
    private TransactionList transactionList = TransactionList.getInstance();

    // This fragment shows all of the transactions where the current user was involved and if
    // the current user is admin all transactions are shown

    public History() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        historyMsg = (TextView) view.findViewById(R.id.historyMsg);
        for(Transaction item : transactionList.getTransactions()) {
            // Check user involvement in transactions or if the user is admin
            if(item.getPayer().equals(bank.getCurrentUser().getName()) || item.getReceiver().equals(bank.getCurrentUser().getName()) || bank.getCurrentUserId() == 0) {
                // Check for transaction type and print corresponding message
                if(item.getTransactionType().equals("Deposit") || item.getTransactionType().equals("Withdrawal")) {
                    historyMsg.append("Type: " + item.getTransactionType() + "\n");
                    historyMsg.append("User: " + item.getPayer() + "\n");
                    historyMsg.append("Account number: " + item.getPayerAccount() + "\n");
                    historyMsg.append("Card: " + item.getPayerCard() + "\n");
                    historyMsg.append("Amount: " + item.getAmount() + "€\n\n");
                } else if(item.getTransactionType().equals("Transfer")) {
                    historyMsg.append("Type: " + item.getTransactionType() + "\n");
                    historyMsg.append("Payer: " + item.getPayer() + "\n");
                    historyMsg.append("Payer's account number: " + item.getPayerAccount() + "\n");
                    historyMsg.append("Receiver: " + item.getReceiver() + "\n");
                    historyMsg.append("Receiver's account number: " + item.getReceiverAccount() + "\n");
                    historyMsg.append("Amount: " + item.getAmount() + "€\n\n");
                } else if(item.getTransactionType().equals("Payment")) {
                    historyMsg.append("Type: " + item.getTransactionType() + "\n");
                    historyMsg.append("Payer: " + item.getPayer() + "\n");
                    historyMsg.append("Account number: " + item.getPayerAccount() + "\n");
                    historyMsg.append("Receiver: " + item.getReceiver() + "\n");
                    historyMsg.append("Card: " + item.getPayerCard() + "\n");
                    historyMsg.append("Amount: " + item.getAmount() + "€\n\n");
                }
            }
        }
        return view;
    }

}
