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
import com.example.juhoh.bankapp.User;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ModifyUsers extends Fragment {
    Spinner modUserSpinner;
    EditText modEditName;
    EditText modEditEmail;
    EditText modEditAddress;
    EditText modEditCity;
    EditText modEditZip;
    EditText modEditNewPassword;
    Button modApplyButton;
    TextView modMsg;
    private Bank bank = Bank.getInstance();
    private User user;

    // Modify the settings of the selected users

    public ModifyUsers() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_modify_users, container, false);

        modUserSpinner = (Spinner) view.findViewById(R.id.modUserSpinner);
        modEditName = (EditText) view.findViewById(R.id.modEditName);
        modEditEmail = (EditText) view.findViewById(R.id.modEditEmail);
        modEditAddress = (EditText) view.findViewById(R.id.modEditAddress);
        modEditCity = (EditText) view.findViewById(R.id.modEditCity);
        modEditZip = (EditText) view.findViewById(R.id.modEditZip);
        modEditNewPassword = (EditText) view.findViewById(R.id.modEditNewPassword);
        modApplyButton = (Button) view.findViewById(R.id.modApplyButton);
        modMsg = (TextView) view.findViewById(R.id.modMsg);

        Spinner dropdown = view.findViewById(R.id.modUserSpinner);
        final ArrayList<User> userList = bank.getUserlist();
        ArrayAdapter<User> adapter = new ArrayAdapter<User>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, userList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);

        // Listens to selected item of user menu and shows user's details
        modUserSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Modify the details of selected user
                for(User item : userList) {
                    if(item.getName().equals(modUserSpinner.getSelectedItem().toString())) {
                        user = item;
                    }
                }

                modEditName.setText(user.getName(), TextView.BufferType.EDITABLE);
                modEditEmail.setText(user.getEmail(), TextView.BufferType.EDITABLE);
                if (user.getAddress().length() != 0) {
                    modEditAddress.setText(user.getAddress(), TextView.BufferType.EDITABLE);
                }
                if (user.getCity().length() != 0) {
                    modEditCity.setText(user.getCity(), TextView.BufferType.EDITABLE);
                }
                if (user.getZip().length() != 0) {
                    modEditZip.setText(user.getZip(), TextView.BufferType.EDITABLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });

        // Listens to clicks on the apply changes -button
        modApplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Change details according to changes the user has made
                if (!modEditName.getText().toString().equals("")) {
                    user.setName(modEditName.getText().toString());
                }
                if (!modEditEmail.getText().toString().equals("")) {
                    user.setEmail(modEditEmail.getText().toString());
                }
                if (!modEditAddress.getText().toString().equals("")) {
                    user.setAddress(modEditAddress.getText().toString());
                }
                if (!modEditCity.getText().toString().equals("")) {
                    user.setCity(modEditCity.getText().toString());
                }
                if (!modEditZip.getText().toString().equals("")) {
                    user.setZip(modEditZip.getText().toString());
                }
                if (!modEditNewPassword.getText().toString().equals("")) {
                    user.setPassword(modEditNewPassword.getText().toString());
                }
                modMsg.setText("New settings applied for user '" + user.getName() + "'.");
            }
        });
        return view;
    }
}
