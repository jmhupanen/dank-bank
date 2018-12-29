package com.example.juhoh.bankapp.Fragments;


import android.os.Bundle;
import android.support.design.widget.NavigationView;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class Login extends Fragment {
    TextView loginStatus;
    TextView message;
    EditText editLoginEmail;
    EditText editLoginPassword;
    Button button;
    private Bank bank;
    private User currentUser;

    // Login activity happens in this fragment

    public Login() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        loginStatus = (TextView) view.findViewById(R.id.loginStatus);
        setLoginStatus();
        message = (TextView) view.findViewById(R.id.message);
        editLoginEmail = (EditText) view.findViewById(R.id.editLoginEmail);
        editLoginPassword = (EditText) view.findViewById(R.id.editLogInPassword);

        button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(User user : bank.getUserlist()) {
                    if(user.getEmail().equals(editLoginEmail.getText().toString())) {
                        // On successful login
                        if (user.getPassword().equals(editLoginPassword.getText().toString())) {
                            bank.setCurrentUserId(user.getUserId());
                            bank.setCurrentUser(user);
                            message.setText("Login successful! Welcome back, " + user.getName() + ".");
                            loginStatus.setText("Currently logged in as " + user.getName() + ".");
                            editLoginEmail.getText().clear();
                            editLoginPassword.getText().clear();
                        } // On wrong password
                        else {
                            message.setText("Wrong password!");
                            editLoginPassword.getText().clear();
                        }
                        return;
                    }
                }
                // If user is not found print this
                message.setText("User not found.");
                editLoginEmail.getText().clear();
                editLoginPassword.getText().clear();
            }
        });

        return view;
    }

    // Set message of who currently logged in
    private void setLoginStatus() {
        bank = Bank.getInstance();
        User user = bank.getCurrentUser();
        if(user.getName().equals("")) {
            loginStatus.setText("Nobody is currently logged in.");
        }
        else {
            loginStatus.setText("Currently logged in as " + user.getName() + ".");
        }
    }

}
