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
import com.example.juhoh.bankapp.User;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class Settings extends Fragment {
    EditText sEditName;
    EditText sEditEmail;
    EditText sEditAddress;
    EditText sEditCity;
    EditText sEditZip;
    EditText sEditPassword;
    EditText sEditNewPassword;
    Button applyButton;
    TextView settingsMsg;
    private Bank bank = Bank.getInstance();
    private User user;

    // Settings activity

    public Settings() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        user = bank.getCurrentUser();

        sEditName = (EditText) view.findViewById(R.id.sEditName);
        sEditEmail = (EditText) view.findViewById(R.id.sEditEmail);
        sEditAddress = (EditText) view.findViewById(R.id.sEditAddress);
        sEditCity = (EditText) view.findViewById(R.id.sEditCity);
        sEditZip = (EditText) view.findViewById(R.id.sEditZip);
        sEditPassword = (EditText) view.findViewById(R.id.sEditPassword);
        sEditNewPassword = (EditText) view.findViewById(R.id.sEditNewPassword);
        applyButton = (Button) view.findViewById(R.id.applyButton);
        settingsMsg = (TextView) view.findViewById(R.id.settingsMsg);

        sEditName.setText(user.getName(), TextView.BufferType.EDITABLE);
        sEditEmail.setText(user.getEmail(), TextView.BufferType.EDITABLE);
        if(user.getAddress().length() != 0) {
            sEditAddress.setText(user.getAddress(), TextView.BufferType.EDITABLE);
        } if(user.getCity().length() != 0) {
            sEditCity.setText(user.getCity(), TextView.BufferType.EDITABLE);
        } if(user.getZip().length() != 0) {
            sEditZip.setText(user.getZip(), TextView.BufferType.EDITABLE);
        }

        // Listen to apply button
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Change settings of current user
                if(!sEditPassword.getText().toString().equals(user.getPassword())) {
                    settingsMsg.setText("Wrong password.");
                } else {
                    if(!sEditName.getText().toString().equals("")) {
                        user.setName(sEditName.getText().toString());
                    } if(!sEditEmail.getText().toString().equals("")) {
                        user.setEmail(sEditEmail.getText().toString());
                    } if(!sEditAddress.getText().toString().equals("")) {
                        user.setAddress(sEditAddress.getText().toString());
                    } if(!sEditCity.getText().toString().equals("")) {
                        user.setCity(sEditCity.getText().toString());
                    } if(!sEditZip.getText().toString().equals("")) {
                        user.setZip(sEditZip.getText().toString());
                    } if(!sEditNewPassword.getText().toString().equals("")) {
                        user.setPassword(sEditNewPassword.getText().toString());
                    }
                    settingsMsg.setText("New settings applied.");
                }
            }
        });
        return view;
    }

}
