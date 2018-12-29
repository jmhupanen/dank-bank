package com.example.juhoh.bankapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.juhoh.bankapp.Account;
import com.example.juhoh.bankapp.Bank;
import com.example.juhoh.bankapp.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewCard extends Fragment {
    Spinner accountSpinner;
    EditText editCardNum;
    EditText editPin;
    EditText editPaym;
    EditText editWith;
    Button buttonAddCard;
    TextView newCardMsg;
    private Bank bank = Bank.getInstance();
    private Account account;

    // This fragment handles new card activity

    public NewCard() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_card, container, false);
        Spinner dropdown = view.findViewById(R.id.accountSpinner);
        final ArrayList<Account> accountList = bank.getCurrentUser().getAccountList();
        ArrayAdapter<Account> adapter = new ArrayAdapter<Account>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, accountList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);
        accountSpinner = (Spinner) view.findViewById(R.id.accountSpinner);
        editCardNum = (EditText) view.findViewById(R.id.editCardNum);
        editPin = (EditText) view.findViewById(R.id.editPin);
        editPaym = (EditText) view.findViewById(R.id.editPaym);
        editWith = (EditText) view.findViewById(R.id.editWith);
        newCardMsg = (TextView) view.findViewById(R.id.newCardMsg);
        buttonAddCard = (Button) view.findViewById(R.id.buttonAddCard);

        // Listens to add card -button
        buttonAddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add a new card with set card number and PIN code
                String cardNumber = editCardNum.getText().toString();
                String pinCode = editPin.getText().toString();
                if(cardNumber.length() != 8 || pinCode.length() != 4 || editPaym.getText().toString().equals("") || editWith.getText().toString().equals("") || accountSpinner.getSelectedItem() == null) {
                    newCardMsg.setText("Make sure the account number is 8 and PIN is 4 characters long, and payment and withdrawal limits have been set.");
                }
                else {
                    int paymentLimit = Integer.parseInt(editPaym.getText().toString());
                    int withdrawalLimit = Integer.parseInt(editWith.getText().toString());
                    for(Account item : accountList) {
                        if(item.getAccountNumber().equals(accountSpinner.getSelectedItem().toString())) {
                            account = item;
                            break;
                        }
                    }
                    account.addCard(cardNumber, pinCode, paymentLimit, withdrawalLimit);
                    editCardNum.getText().clear();
                    editPin.getText().clear();
                    editPaym.getText().clear();
                    editWith.getText().clear();
                    newCardMsg.setText("New card " + cardNumber + " has been added to account " + accountSpinner.getSelectedItem().toString() + ".");
                }
            }
        });
        return view;
    }

}
