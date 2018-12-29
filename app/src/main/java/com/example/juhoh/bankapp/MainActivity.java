package com.example.juhoh.bankapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.juhoh.bankapp.Fragments.DepWit;
import com.example.juhoh.bankapp.Fragments.History;
import com.example.juhoh.bankapp.Fragments.Login;
import com.example.juhoh.bankapp.Fragments.ManageUsers;
import com.example.juhoh.bankapp.Fragments.ModifyUsers;
import com.example.juhoh.bankapp.Fragments.NewAcc;
import com.example.juhoh.bankapp.Fragments.NewCard;
import com.example.juhoh.bankapp.Fragments.NewPay;
import com.example.juhoh.bankapp.Fragments.NewTra;
import com.example.juhoh.bankapp.Fragments.Settings;
import com.example.juhoh.bankapp.Fragments.Signup;
import com.example.juhoh.bankapp.Fragments.UserList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private TextView textName;
    private TextView textEmail;
    private Bank bank = Bank.getInstance();

    // This class controls the UI and which fragment is shown at the moment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // Navigation view is declared here
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headView = navigationView.getHeaderView(0);
        textName = (TextView) headView.findViewById(R.id.textName);
        textEmail = (TextView) headView.findViewById(R.id.textEmail);
        final Menu navMenu = navigationView.getMenu();
        drawer.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(@NonNull View view, float v) {
                        // Set the navigation drawer profile name to same value as current user
                        textName.setText(bank.getCurrentUser().getName());
                        textEmail.setText(bank.getCurrentUser().getEmail());
                        // Manage menu item visibility by who is the current user
                        if(bank.getCurrentUserId() == -1) {
                            navMenu.findItem(R.id.nav_login).setVisible(true);
                            navMenu.findItem(R.id.nav_signup).setVisible(true);
                            navMenu.findItem(R.id.nav_newacc).setVisible(false);
                            navMenu.findItem(R.id.nav_newcard).setVisible(false);
                            navMenu.findItem(R.id.nav_depwit).setVisible(false);
                            navMenu.findItem(R.id.nav_newtra).setVisible(false);
                            navMenu.findItem(R.id.nav_newpay).setVisible(false);
                            navMenu.findItem(R.id.nav_history).setVisible(false);
                            navMenu.findItem(R.id.nav_settings).setVisible(false);
                            navMenu.findItem(R.id.nav_manageusers).setVisible(false);
                            navMenu.findItem(R.id.nav_modifyusers).setVisible(false);
                            navMenu.findItem(R.id.nav_userlist).setVisible(false);
                        } else if(bank.getCurrentUserId() == 0) {
                            navMenu.findItem(R.id.nav_login).setVisible(true);
                            navMenu.findItem(R.id.nav_signup).setVisible(true);
                            navMenu.findItem(R.id.nav_newacc).setVisible(true);
                            navMenu.findItem(R.id.nav_newcard).setVisible(true);
                            navMenu.findItem(R.id.nav_depwit).setVisible(true);
                            navMenu.findItem(R.id.nav_newtra).setVisible(true);
                            navMenu.findItem(R.id.nav_newpay).setVisible(true);
                            navMenu.findItem(R.id.nav_history).setVisible(true);
                            navMenu.findItem(R.id.nav_settings).setVisible(true);
                            navMenu.findItem(R.id.nav_manageusers).setVisible(true);
                            navMenu.findItem(R.id.nav_modifyusers).setVisible(true);
                            navMenu.findItem(R.id.nav_userlist).setVisible(true);
                        } else {
                            navMenu.findItem(R.id.nav_login).setVisible(true);
                            navMenu.findItem(R.id.nav_signup).setVisible(true);
                            navMenu.findItem(R.id.nav_newacc).setVisible(true);
                            navMenu.findItem(R.id.nav_newcard).setVisible(true);
                            navMenu.findItem(R.id.nav_depwit).setVisible(true);
                            navMenu.findItem(R.id.nav_newtra).setVisible(true);
                            navMenu.findItem(R.id.nav_newpay).setVisible(true);
                            navMenu.findItem(R.id.nav_history).setVisible(true);
                            navMenu.findItem(R.id.nav_settings).setVisible(true);
                            navMenu.findItem(R.id.nav_manageusers).setVisible(false);
                            navMenu.findItem(R.id.nav_modifyusers).setVisible(false);
                            navMenu.findItem(R.id.nav_userlist).setVisible(false);
                        }
                    }

                    @Override
                    public void onDrawerOpened(@NonNull View view) {

                    }

                    @Override
                    public void onDrawerClosed(@NonNull View view) {

                    }

                    @Override
                    public void onDrawerStateChanged(int i) {

                    }
                }
        );
        // Default fragment at app start
        setTitle("Log in");
        Login login = new Login();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fram, login, "Login");
        fragmentTransaction.commit();
    }

    @Override
    public void onResume(){
        super.onResume();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // Set the navigation drawer profile name to same value as current user
        View headView = navigationView.getHeaderView(0);
        TextView textName = (TextView) headView.findViewById(R.id.textName);
        TextView textEmail = (TextView) headView.findViewById(R.id.textEmail);
        textName.setText(bank.getCurrentUser().getName());
        textEmail.setText(bank.getCurrentUser().getEmail());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Navigation view item clicks are handled here and shown fragment is set by
        // selected item
        int id = item.getItemId();

        if (id == R.id.nav_login) {
            setTitle("Log in/Change user");
            Login login = new Login();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fram, login, "Login");
            fragmentTransaction.commit();
        } else if (id == R.id.nav_signup) {
            setTitle("Sign up");
            Signup signup = new Signup();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fram, signup, "Singup");
            fragmentTransaction.commit();
        } else if (id == R.id.nav_newacc) {
            setTitle("New account");
            NewAcc newacc = new NewAcc();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fram, newacc, "NewAcc");
            fragmentTransaction.commit();
        } else if (id == R.id.nav_newcard) {
            setTitle("New debit card");
            NewCard newcard = new NewCard();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fram, newcard, "NewCard");
            fragmentTransaction.commit();
        } else if (id == R.id.nav_depwit) {
            setTitle("Deposit/Withraw");
            DepWit depwit = new DepWit();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fram, depwit, "DepWit");
            fragmentTransaction.commit();
        } else if (id == R.id.nav_newtra) {
            setTitle("New transfer");
            NewTra newtra = new NewTra();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fram, newtra, "NewTra");
            fragmentTransaction.commit();
        } else if (id == R.id.nav_newpay) {
            setTitle("New payment");
            NewPay newpay = new NewPay();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fram, newpay, "NewPay");
            fragmentTransaction.commit();
        } else if (id == R.id.nav_history) {
            setTitle("Transaction history");
            History history = new History();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fram, history, "History");
            fragmentTransaction.commit();
        } else if (id == R.id.nav_settings) {
            setTitle("Settings");
            Settings settings = new Settings();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fram, settings, "Settings");
            fragmentTransaction.commit();
        } else if (id == R.id.nav_manageusers) {
            setTitle("Manage users");
            ManageUsers manageusers = new ManageUsers();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fram, manageusers, "ManageUsers");
            fragmentTransaction.commit();
        } else if (id == R.id.nav_modifyusers) {
            setTitle("Modify user details");
            ModifyUsers modifyusers = new ModifyUsers();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fram, modifyusers, "ModifyUsers");
            fragmentTransaction.commit();
        } else if (id == R.id.nav_userlist) {
            setTitle("User list");
            UserList userlist = new UserList();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fram, userlist, "UserList");
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
