package com.example.juhoh.bankapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.juhoh.bankapp.Account;
import com.example.juhoh.bankapp.Bank;
import com.example.juhoh.bankapp.Card;
import com.example.juhoh.bankapp.R;
import com.example.juhoh.bankapp.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserList extends Fragment {
    TextView ulMsg;
    private Bank bank = Bank.getInstance();

    // Make a list of all users and their details, accounts and cards

    public UserList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_list, container, false);

        ulMsg = (TextView) view.findViewById(R.id.ulMsg);
        ulMsg.append("*********************************************************\n");
        for(User user : bank.getUserlist()) {
            ulMsg.append("Name: " + user.getName() + "\n");
            ulMsg.append("Email: " + user.getEmail() + "\n");
            if(!user.getAddress().equals("")) {
                ulMsg.append("Address: " + user.getAddress() + "\n");
            }
            if(!user.getCity().equals("")) {
                ulMsg.append("City: " + user.getCity() + "\n");
            }
            if(!user.getZip().equals("")) {
                ulMsg.append("ZIP code: " + user.getZip() + "\n");
            }
            if(!user.getAccountList().isEmpty()) {
                ulMsg.append("\nAccounts: \n");
                for(Account account : user.getAccountList()) {
                    ulMsg.append("***************************\n");
                    ulMsg.append("Account number: " + account.getAccountNumber() + "\n");
                    ulMsg.append("Account type: " + account.getAccountType() + "\n");
                    ulMsg.append("Account balance: " + account.getBalance() + "€\n");
                    if(account.isFrozen()) {
                        ulMsg.append("Account status: FROZEN\n");
                    }
                    ulMsg.append("\n");
                    if(!account.getCardList().isEmpty()) {
                        ulMsg.append("Cards: ");
                        for(Card card : account.getCardList()) {
                            ulMsg.append("\nCard number: " + card.getCardNumber() + "\n");
                            ulMsg.append("Payment limit: " + card.getPaymentLimit() + "€\n");
                            ulMsg.append("Withdrawal limit: " + card.getWithdrawalLimit() + "€\n");
                        }
                    }
                }
            }
            ulMsg.append("*********************************************************\n");
        }
        return view;
    }

}
