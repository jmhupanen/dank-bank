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

import com.example.juhoh.bankapp.Bank;
import com.example.juhoh.bankapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewAcc extends Fragment {
    EditText editAccNum;
    Spinner spinner;
    Button buttonCreaAcc;
    TextView newAccMsg;
    private Bank bank = Bank.getInstance();

    // This fragment handles new account activity

    public NewAcc() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_acc, container, false);
        Spinner dropdown = view.findViewById(R.id.spinner);
        String[] items = new String[]{"Choose account type", "Current account", "Saving account"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        editAccNum = (EditText) view.findViewById(R.id.editAccNum);
        spinner = (Spinner) view.findViewById(R.id.spinner);
        newAccMsg = (TextView) view.findViewById(R.id.newAccMsg);

        buttonCreaAcc = (Button) view.findViewById(R.id.buttonCreaAcc);
        buttonCreaAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create new account and get the settings set by user
                String accountNumber = editAccNum.getText().toString();
                String accountType = spinner.getSelectedItem().toString();
                if(accountNumber.length() != 8 || accountType.equals("Choose account type")) {
                    newAccMsg.setText("Make sure the account number is exactly 8 characters long and account type is selected.");
                }
                else {
                    bank.addAccount(accountNumber, accountType);
                    editAccNum.getText().clear();
                    spinner.setSelection(0);
                    newAccMsg.setText("New " + accountType + " " + accountNumber + " has been created.");
                }
            }
        });
        return view;
    }

}
