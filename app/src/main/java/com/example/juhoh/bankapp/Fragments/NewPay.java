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
import com.example.juhoh.bankapp.Card;
import com.example.juhoh.bankapp.R;
import com.example.juhoh.bankapp.TransactionList;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewPay extends Fragment {
    Spinner npAccountSpinner;
    Spinner npCardSpinner;
    EditText editReceiver;
    EditText npEditAmount;
    EditText npEditPin;
    Button payButton;
    TextView newPayMsg;
    private Bank bank = Bank.getInstance();
    private TransactionList transactionList = TransactionList.getInstance();
    private Account account;
    private Card card;

    // Handles new payment activity

    public NewPay() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_pay, container, false);

        npAccountSpinner = (Spinner) view.findViewById(R.id.npAccountSpinner);

        Spinner dropdown = view.findViewById(R.id.npAccountSpinner);
        final ArrayList<Account> accountList = bank.getCurrentUser().getAccountList();
        ArrayAdapter<Account> adapter = new ArrayAdapter<Account>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, accountList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);

        editReceiver = (EditText) view.findViewById(R.id.editReceiver);
        npEditAmount = (EditText) view.findViewById(R.id.npEditAmount);
        npEditPin = (EditText) view.findViewById(R.id.npEditPin);
        payButton = (Button) view.findViewById(R.id.payButton);
        newPayMsg = (TextView) view.findViewById(R.id.newPayMsg);
        npCardSpinner = (Spinner) view.findViewById(R.id.npCardSpinner);

        final Spinner dropdown2 = (Spinner) view.findViewById(R.id.npCardSpinner);
        // Listen to selected account and show its cards
        npAccountSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for(Account item : accountList) {
                    if(item.getAccountNumber().equals(npAccountSpinner.getSelectedItem().toString())) {
                        account = item;
                        break;
                    }
                }
                final ArrayList<Card> cardList = account.getCardList();
                ArrayAdapter<Card> adapter2 = new ArrayAdapter<Card>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, cardList);
                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dropdown2.setAdapter(adapter2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Listen to pay button
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(Card item : account.getCardList()) {
                    if(item.getCardNumber().equals(npCardSpinner.getSelectedItem().toString())) {
                        card = item;
                        break;
                    }
                }

                // Check if all fields are filled
                if(npAccountSpinner.getSelectedItem() == null || npCardSpinner.getSelectedItem() == null || editReceiver.getText().toString().equals("") || npEditAmount.getText().toString().equals("")) {
                    newPayMsg.setText("Make sure that all fields are filled.");
                }
                else {
                    int amount = Integer.parseInt(npEditAmount.getText().toString());

                    if(!npEditPin.getText().toString().equals(card.getPinCode())) {
                        newPayMsg.setText("Wrong PIN code.");
                    } else if (account.isFrozen()) {
                        newPayMsg.setText("This account is frozen.");
                    } else if (account.getAccountType().equals("Saving account")) {
                        newPayMsg.setText("You can't make payments from Savings account.");
                    } else if (amount > card.getPaymentLimit()) {
                        newPayMsg.setText("Your payment limit is " + card.getPaymentLimit() + "€.");
                    } else if (amount > account.getBalance()) {
                        newPayMsg.setText("This account doesn't have enough money for this payment.");
                    } else if (amount == 0) {
                        newPayMsg.setText("The amount has to be more than 0€.");
                    } else {
                        // actual payment happens here
                        account.reduceMoney(amount);
                        newPayMsg.setText("Your payment for " + amount + "€ to " + editReceiver.getText().toString() + " from account " + account.getAccountNumber() + " has been completed.");
                        transactionList.addPayment(bank.getCurrentUser().getName(), account.getAccountNumber(), editReceiver.getText().toString(), card.getCardNumber(), amount);
                    }
                }
            }
        });
        return view;
    }

}
