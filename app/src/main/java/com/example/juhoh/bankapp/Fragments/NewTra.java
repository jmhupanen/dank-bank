package com.example.juhoh.bankapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.juhoh.bankapp.Account;
import com.example.juhoh.bankapp.Bank;
import com.example.juhoh.bankapp.R;
import com.example.juhoh.bankapp.TransactionList;
import com.example.juhoh.bankapp.User;

import org.w3c.dom.Text;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewTra extends Fragment {
    Spinner userSpinner;
    Spinner accountSpinner3;
    EditText editAmount2;
    Spinner spinner4;
    Button transferButton;
    TextView newTraMsg;
    private Bank bank = Bank.getInstance();
    private TransactionList transactionList = TransactionList.getInstance();
    private User user;
    private Account payerAccount;
    private Account receiverAccount;

    // New transfer activity

    public NewTra() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_tra, container, false);

        Spinner dropdown = view.findViewById(R.id.userSpinner);
        final ArrayList<User> userList = bank.getUserlist();
        ArrayAdapter<User> adapter = new ArrayAdapter<User>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, userList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);

        Spinner dropdown2 = view.findViewById(R.id.spinner4);
        final ArrayList<Account> accountList = bank.getCurrentUser().getAccountList();
        ArrayAdapter<Account> adapter2 = new ArrayAdapter<Account>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, accountList);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown2.setAdapter(adapter2);

        userSpinner = (Spinner) view.findViewById(R.id.userSpinner);
        accountSpinner3 = (Spinner) view.findViewById(R.id.accountSpinner3);
        editAmount2 = (EditText) view.findViewById(R.id.editAmount2);
        spinner4 = (Spinner) view.findViewById(R.id.spinner4);
        transferButton = (Button) view.findViewById(R.id.transferButton);
        newTraMsg = (TextView) view.findViewById(R.id.newTraMsg);

        final Spinner dropdown3 =  view.findViewById(R.id.accountSpinner3);
        // Listen to user menu and show its accounts
        userSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                for(User item : userList) {
                    if(item.getName().equals(userSpinner.getSelectedItem().toString())) {
                        user = item;
                        break;
                    }
                }
                final ArrayList<Account> accountList2 = user.getAccountList();
                ArrayAdapter<Account> adapter3 = new ArrayAdapter<Account>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, accountList2);
                adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dropdown3.setAdapter(adapter3);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        // Listen to transfer button
        transferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(accountSpinner3.getSelectedItem() == null || spinner4.getSelectedItem() == null || editAmount2.getText().toString().equals("")) {
                    newTraMsg.setText("Make sure that all fields are filled.");
                }
                else if(accountSpinner3.getSelectedItem().toString().equals(spinner4.getSelectedItem().toString())) {
                    newTraMsg.setText("You can't transfer money inside the same account.");
                }
                else {
                    // Check if transfer can be made
                    int amount = Integer.parseInt(editAmount2.getText().toString());

                    for (Account item : accountList) {
                        if (item.getAccountNumber().equals(spinner4.getSelectedItem().toString())) {
                            payerAccount = item;
                            break;
                        }
                    }
                     if (payerAccount.isFrozen()) {
                        newTraMsg.setText("Payer's account is frozen.");
                    } else if(payerAccount.getAccountType().equals("Saving account")) {
                        newTraMsg.setText("You can't make transfers from saving account.");
                    } else if(payerAccount.getBalance() < amount) {
                        newTraMsg.setText("This account doesn't have enough money for this transfer.");
                    } else if(amount == 0) {
                        newTraMsg.setText("The amount has to be more than 0€.");
                    } else {
                        for (User item : userList) {
                            if (item.getName().equals(userSpinner.getSelectedItem().toString())) {
                                user = item;
                                ArrayList<Account> accountList2 = user.getAccountList();
                                for (Account item2 : accountList2) {
                                    if (item2.getAccountNumber().equals(accountSpinner3.getSelectedItem().toString())) {
                                        receiverAccount = item2;
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        if (receiverAccount.isFrozen()) {
                            newTraMsg.setText("Receivers's account is frozen.");
                        } else {
                            // actual transfer happens here
                            payerAccount.reduceMoney(amount);
                            receiverAccount.addMoney(amount);
                            newTraMsg.setText(amount + "€ added to " + user.getName() + "'s account " + receiverAccount.getAccountNumber() + ".");
                            transactionList.addTransfer(bank.getCurrentUser().getName(), payerAccount.getAccountNumber(), user.getName(), receiverAccount.getAccountNumber(), amount);
                        }
                    }
                }
            }
        });

        return view;
    }

}
