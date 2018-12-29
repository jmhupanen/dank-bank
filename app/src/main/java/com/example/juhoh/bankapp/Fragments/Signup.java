package com.example.juhoh.bankapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.juhoh.bankapp.Bank;
import com.example.juhoh.bankapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Signup extends Fragment {
    EditText editName;
    EditText editCity;
    EditText editZip;
    EditText editAddress;
    EditText editEmail;
    EditText editPassword;
    TextView message;
    Button button;

    // Signup activity

    public Signup() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        editName = (EditText) view.findViewById(R.id.editName);
        editCity = (EditText) view.findViewById(R.id.editCity);
        editZip = (EditText) view.findViewById(R.id.editZip);
        editAddress = (EditText) view.findViewById(R.id.editAddress);
        editEmail = (EditText) view.findViewById(R.id.editEmail);
        editPassword = (EditText) view.findViewById(R.id.editPassword);
        message = (TextView) view.findViewById(R.id.message);
        button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create new account
                if(editName.getText().length() == 0 || editEmail.getText().length() == 0 || editPassword.getText().length() == 0) {
                    message.setText("Please complete all fields marked by *.");
                }
                else {
                    Bank bank = Bank.getInstance();
                    bank.addUser(editName.getText().toString(), editCity.getText().toString(), editZip.getText().toString(), editAddress.getText().toString(), editEmail.getText().toString(), editPassword.getText().toString());
                    message.setText("Welcome to use our bank app, " + editName.getText().toString() + "! Select the \"Login/Change user\" option in the menu to Log in.");
                    editName.getText().clear();
                    editCity.getText().clear();
                    editZip.getText().clear();
                    editAddress.getText().clear();
                    editEmail.getText().clear();
                    editPassword.getText().clear();
                }
            }
        });
        return view;
    }

}
