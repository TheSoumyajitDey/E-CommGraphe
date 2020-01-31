package com.example.e_commgraphe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AdminPage extends AppCompatActivity {

    private  Button adminLoginButton;
    private EditText adminPhoneNumber;
    private EditText adminPassword;
    private ProgressDialog loadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
        //where all the connections are made with the XML sheet of the android page
        adminLoginButton = (Button)findViewById(R.id.adminLogin);
        adminPhoneNumber = (EditText)findViewById(R.id.admin_login_phone_number_input);
        adminPassword = (EditText)findViewById(R.id.admin_login_password_input);

        //loading bar initialized
        loadingBar = new ProgressDialog(this);

       // when Admin Login Button and all the actions that are taken 
        adminLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });









    }
}
