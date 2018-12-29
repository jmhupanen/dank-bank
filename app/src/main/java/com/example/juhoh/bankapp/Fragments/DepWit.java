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

import org.w3c.dom.Text;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DepWit extends Fragment {
    Spinner accountSpinner2;
    Spinner cardSpinner;
    EditText editAmount;
    EditText editPin2;
    Button depositButton;
    Button withdrawalButton;
    TextView depWitMsg;
    private Bank bank = Bank.getInstance();
    private TransactionList transactionList = TransactionList.getInstance();
    private Account account;
    private Card card;

    public DepWit() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_dep_wit, container, false);

        final Spinner dropdown = view.findViewById(R.id.accountSpinner2);
        final ArrayList<Account> accountList = bank.getCurrentUser().getAccountList();
        ArrayAdapter<Account> adapter = new ArrayAdapter<Account>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, accountList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);

        accountSpinner2 = (Spinner) view.findViewById(R.id.accountSpinner2);
        cardSpinner = (Spinner) view.findViewById(R.id.cardSpinner);
        editAmount = (EditText) view.findViewById(R.id.editAmount);
        editPin2 = (EditText) view.findViewById(R.id.editPin2);
        depositButton = (Button) view.findViewById(R.id.depositButton);
        withdrawalButton = (Button) view.findViewById(R.id.withdrawalButton);
        depWitMsg = (TextView) view.findViewById(R.id.depWitMsg);

        // Listener that listens to dropdown menu of accounts and then shows cards that belong to selected account
        final Spinner dropdown2 =  view.findViewById(R.id.cardSpinner);
        accountSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                for(Account item : accountList) {
                    if(item.getAccountNumber().equals(accountSpinner2.getSelectedItem().toString())) {
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
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        // Listener that listens to clicks of deposit button
        depositButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Checks if all options are set correctly
                if(accountSpinner2.getSelectedItem() == null || cardSpinner.getSelectedItem() == null || editAmount.getText().toString().equals("") || editPin2.getText().toString().length() != 4) {
                    depWitMsg.setText("Make sure a card is selected, amount is set and PIN code is 4 characters long.");
                }
                else {
                    for(Account item : accountList) {
                        if(item.getAccountNumber().equals(accountSpinner2.getSelectedItem().toString())) {
                            account = item;
                            ArrayList<Card> cardList = item.getCardList();
                            for(Card item2 : cardList) {
                                if(item2.getCardNumber().equals(cardSpinner.getSelectedItem().toString())) {
                                    card = item2;
                                    break;
                                }
                            }
                            break;
                        }
                    } // If the selected account is frozen, deposit is cancelled
                    if(account.isFrozen()) {
                        depWitMsg.setText("This account is frozen.");
                    } // Actual deposit happens here
                    else if(card.getPinCode().equals(editPin2.getText().toString())) {
                        int amount = Integer.parseInt(editAmount.getText().toString());
                        account.addMoney(amount);
                        editAmount.getText().clear();
                        editPin2.getText().clear();
                        depWitMsg.setText(amount + "€ added to account " + account.getAccountNumber());
                        transactionList.addDepWit("Deposit", bank.getCurrentUser().getName(), account.getAccountNumber(), card.getCardNumber(), amount);
                    } // In case PIN is wrong, show "Wrong PIN" -message
                    else {
                        depWitMsg.setText("Wrong PIN code.");
                    }
                }
            }
        });

        // Listener that listens to clicks of deposit button
        withdrawalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Checks if all options are set correctly
                if(accountSpinner2.getSelectedItem() == null || cardSpinner.getSelectedItem() == null || editAmount.getText().toString().equals("") || editPin2.getText().toString().length() != 4) {
                    depWitMsg.setText("Make sure a card is selected, amount is set and PIN code is 4 characters long.");
                }
                else {
                    for(Account item : accountList) {
                        if(item.getAccountNumber().equals(accountSpinner2.getSelectedItem().toString())) {
                            account = item;
                            ArrayList<Card> cardList = item.getCardList();
                            for(Card item2 : cardList) {
                                if(item2.getCardNumber().equals(cardSpinner.getSelectedItem().toString())) {
                                    card = item2;
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    if(card.getPinCode().equals(editPin2.getText().toString())) {
                        int amount = Integer.parseInt(editAmount.getText().toString());
                        if(account.isFrozen()) {
                            depWitMsg.setText("This account is frozen.");
                        } else if(account.getAccountType().equals("Saving account")) {
                            depWitMsg.setText("You can't withdraw cash from savings account.");
                        }
                        else if(amount > card.getWithdrawalLimit()) {
                            depWitMsg.setText(amount + "€ is too big a sum to withdraw from this account with a card that has a withdrawal limit of " + editAmount.getText().toString() + "€.");
                        } // Actual withdraw happens here
                        else {
                            account.reduceMoney(amount);
                            editAmount.getText().clear();
                            editPin2.getText().clear();
                            depWitMsg.setText(amount + "€ taken from account " + account.getAccountNumber());
                            transactionList.addDepWit("Withdrawal", bank.getCurrentUser().getName(), account.getAccountNumber(), card.getCardNumber(), amount);
                        }
                    }
                    else {
                        depWitMsg.setText("Wrong PIN code.");
                    }
                }
            }
        });
        return view;
    }

}
