package com.example.e_commgraphe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AdminLogicActivity extends AppCompatActivity {


    private Button AdminLoginButton;
    private EditText AdminInputNumber,AdminInputPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_logic);

        AdminLoginButton = (Button)findViewById(R.id.adminhuBhai);
        AdminInputNumber = (EditText)findViewById(R.id.login_phone_number_input);
        AdminInputPassword = (EditText)findViewById(R.id.login_password_input);

        AdminLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }
}
