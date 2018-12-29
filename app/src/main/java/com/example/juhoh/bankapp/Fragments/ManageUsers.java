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
import com.example.juhoh.bankapp.User;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ManageUsers extends Fragment {
    Spinner muUserSpinner;
    Button deleteButton;
    Spinner muUserSpinner2;
    Spinner muAccountSpinner;
    Button deleteButton2;
    Button freezeButton;
    Spinner muUserSpinner3;
    Spinner muAccountSpinner2;
    Spinner muCardSpinner;
    Button deleteButton3;
    TextView muMsg;
    private Bank bank = Bank.getInstance();
    private User user = new User();
    private Account account = new Account();
    private Card card = new Card();

    // Delete and freeze accounts and remove cards and users

    public ManageUsers() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manage_users, container, false);

        muUserSpinner = (Spinner) view.findViewById(R.id.muUserSpinner);
        deleteButton = (Button) view.findViewById(R.id.deleteButton);
        muUserSpinner2 = (Spinner) view.findViewById(R.id.muUserSpinner2);
        muAccountSpinner = (Spinner) view.findViewById(R.id.muAccountSpinner);
        deleteButton2 = (Button) view.findViewById(R.id.deleteButton2);
        freezeButton = (Button) view.findViewById(R.id.freezeButton);
        muUserSpinner3 = (Spinner) view.findViewById(R.id.muUserSpinner3);
        muAccountSpinner2 = (Spinner) view.findViewById(R.id.muAccountSpinner2);
        muCardSpinner = (Spinner) view.findViewById(R.id.muCardSpinner);
        deleteButton3 = (Button) view.findViewById(R.id.deleteButton3);
        muMsg = (TextView) view.findViewById(R.id.muMsg);

        Spinner dropdown = view.findViewById(R.id.muUserSpinner);
        final ArrayList<User> userList = bank.getUserlist();
        ArrayAdapter<User> adapter = new ArrayAdapter<User>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, userList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);

        Spinner dropdown2 = view.findViewById(R.id.muUserSpinner2);
        final ArrayList<User> userList2 = bank.getUserlist();
        ArrayAdapter<User> adapter2 = new ArrayAdapter<User>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, userList2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown2.setAdapter(adapter2);

        // Listens to which one of the users is selected and shows their accounts
        final Spinner dropdown3 =  view.findViewById(R.id.muAccountSpinner);
        muUserSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                for(User item : userList2) {
                    if(item.getName().equals(muUserSpinner2.getSelectedItem().toString())) {
                        user = item;
                        break;
                    }
                }
                final ArrayList<Account> accountList = user.getAccountList();
                ArrayAdapter<Account> adapter3 = new ArrayAdapter<Account>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, accountList);
                adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dropdown3.setAdapter(adapter3);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        Spinner dropdown4 = view.findViewById(R.id.muUserSpinner3);
        final ArrayList<User> userList3 = bank.getUserlist();
        ArrayAdapter<User> adapter4 = new ArrayAdapter<User>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, userList3);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown4.setAdapter(adapter4);

        // Listens to which one of the users is selected and shows their accounts
        final Spinner dropdown5 =  view.findViewById(R.id.muAccountSpinner2);
        muUserSpinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                for(User item : userList3) {
                    if(item.getName().equals(muUserSpinner3.getSelectedItem().toString())) {
                        user = item;
                        break;
                    }
                }
                final ArrayList<Account> accountList2 = user.getAccountList();
                ArrayAdapter<Account> adapter5 = new ArrayAdapter<Account>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, accountList2);
                adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dropdown5.setAdapter(adapter5);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        //Listens to which one of the accounts is selected and shows its cards
        final Spinner dropdown6 =  view.findViewById(R.id.muCardSpinner);
        muAccountSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                for(User item : userList3) {
                    if(item.getName().equals(muUserSpinner3.getSelectedItem().toString())) {
                        user = item;
                        break;
                    }
                }
                final ArrayList<Account> accountList2 = user.getAccountList();

                for(Account item : accountList2) {
                    if(item.getAccountNumber().equals(muAccountSpinner2.getSelectedItem().toString())) {
                        account = item;
                        break;
                    }
                }
                final ArrayList<Card> cardList = account.getCardList();
                ArrayAdapter<Card> adapter6 = new ArrayAdapter<Card>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, cardList);
                adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dropdown6.setAdapter(adapter6);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });

        // Listens to clicks on the first delete button
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Removes every but admin account on click
                for(User item : userList) {
                    if(item.getName().equals(muUserSpinner.getSelectedItem().toString())) {
                        user = item;
                        break;
                    }
                }
                if(user.getUserId() == 0) {
                    muMsg.setText("Admin user cannot be deleted.");
                } else {
                    bank.removeUser(user.getUserId());
                    muUserSpinner.setSelection(0);
                    muMsg.setText("User named '" + user.getName() + "' removed.");
                }
            }
        });

        // Listens to clicks on the second delete button
        deleteButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Removes selected account
                if(muAccountSpinner.getSelectedItem() == null) {
                    muMsg.setText("Account must be selected.");
                }
                else {
                    for(User item : userList) {
                        if(item.getName().equals(muUserSpinner2.getSelectedItem().toString())) {
                            user = item;
                            break;
                        }
                    }
                    for(Account item : user.getAccountList()) {
                        if(item.getAccountNumber().equals(muAccountSpinner.getSelectedItem().toString())) {
                            account = item;
                            break;
                        }
                    }
                    user.removeAccount(account.getAccountNumber());
                    muAccountSpinner.setSelection(0);
                    muMsg.setText("Account " + account.getAccountNumber() + " of user named '" + user.getName() + "' removed.");
                }
            }
        });

        // Listens to clicks on freeze button
        freezeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Freezes selected account
                if(muAccountSpinner.getSelectedItem() == null) {
                    muMsg.setText("Account must be selected.");
                }
                else {
                    for(User item : userList) {
                        if(item.getName().equals(muUserSpinner2.getSelectedItem().toString())) {
                            user = item;
                            break;
                        }
                    }
                    for(Account item : user.getAccountList()) {
                        if(item.getAccountNumber().equals(muAccountSpinner.getSelectedItem().toString())) {
                            account = item;
                            break;
                        }
                    }
                    account.freezeAccount();
                    muMsg.setText("Account " + account.getAccountNumber() + " of user named '" + user.getName() + "' is now frozen.");
                }
            }
        });

        // Listens to clicks on the third delete button
        deleteButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Removes selected card
                if(muAccountSpinner2.getSelectedItem() == null || muCardSpinner.getSelectedItem() == null) {
                    muMsg.setText("Account and card must be selected.");
                }
                else {
                    for(User item : userList) {
                        if(item.getName().equals(muUserSpinner3.getSelectedItem().toString())) {
                            user = item;
                            break;
                        }
                    }
                    for(Account item : user.getAccountList()) {
                        if(item.getAccountNumber().equals(muAccountSpinner2.getSelectedItem().toString())) {
                            account = item;
                            break;
                        }
                    }
                    ArrayList<Card> cardList = account.getCardList();
                    for(Card item : cardList) {
                        if(item.getCardNumber().equals(muCardSpinner.getSelectedItem().toString())) {
                            card = item;
                        }
                    }
                    account.removeCard(card.getCardNumber());
                    muAccountSpinner.setSelection(0);
                    muMsg.setText("Card " + card.getCardNumber() + " bound to account " + account.getAccountNumber() + " of user named '" + user.getName() + "' removed.");
                }
            }
        });
        return view;
    }

}
