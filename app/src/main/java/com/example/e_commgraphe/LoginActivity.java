package com.example.e_commgraphe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.e_commgraphe.Prevalent.prevalent;
import com.example.e_commgraphe.model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;


public class LoginActivity extends AppCompatActivity {
    private EditText InputNumber,InputPassword;
    private Button LoginButton,AdminLogicButton;
    private ProgressDialog loadingBar;
    private String parentDbName = "Users";
    private CheckBox chkBoxRememberME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginButton = (Button) findViewById(R.id.mwork);
        AdminLogicButton = (Button) findViewById(R.id.adminhuBhai);
        InputNumber = (EditText)findViewById(R.id.login_phone_number_input);
        InputPassword = (EditText)findViewById(R.id.login_password_input);
        chkBoxRememberME = (CheckBox)findViewById(R.id.rememberMe);

        loadingBar = new ProgressDialog(this);

        Paper.init(this);


        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUsers();
            }
        });

//        AdminLogicButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LoginActivity.this,AdminLogicActivity.class);
//                startActivity(intent);
//            }
//        });



    }

    private void LoginUsers(){
        String phone = InputNumber.getText().toString();
        String password = InputPassword.getText().toString();

        if(TextUtils.isEmpty(phone)){
            Toast.makeText(LoginActivity.this,"Please Enter Your Valid Phone Number",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password)){
            Toast.makeText(LoginActivity.this,"Please Enter Your Valid Password",Toast.LENGTH_SHORT).show();

        }else {
            loadingBar.setTitle("Login Account");
            loadingBar.setMessage("We are arranging things for you! Just a Moment!");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            AllowAccessToAccount(phone,password);
        }

    }

    private void AllowAccessToAccount(final String phone, final String password) {

        if(chkBoxRememberME.isChecked()){
            Paper.book().write(prevalent.UserPhoneKey,phone);
            Paper.book().write(prevalent.UserPasswordKey,password);

        }
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference().child("Users").child(phone);

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                if((dataSnapshot.child("phone").getValue().toString()).equals(phone)){
                    if((dataSnapshot.child("password").getValue().toString()).equals(password)){

                        Intent inti = new Intent(LoginActivity.this,HomeActivity.class);
                        startActivity(inti);

                        Toast.makeText(LoginActivity.this,"Welcome back ! Logged in Successfully",Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();


                    }else {

                        Toast.makeText(LoginActivity.this,"Sorry Your password you enterered is WRONG!",Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();

                    }




//                    Users userData = dataSnapshot.child("Users").child(phone).getValue(Users.class);
//                    if(userData.getPhone().equals(phone) && userData.getPassword().equals(password)){
//                        if(dataSnapshot.child("Users").child(phone).child(password).exists()){
//
//                            Intent inti = new Intent(LoginActivity.this,testActivity.class);
//                            startActivity(inti);
//
//                            Toast.makeText(LoginActivity.this,"Welcome back ! Logged in Successfully",Toast.LENGTH_SHORT).show();
//                            loadingBar.dismiss();
//
//
//                        }

////                        if(userData.getPassword().equals(password)){

//                        Intent inti = new Intent(LoginActivity.this,testActivity.class);
//                        startActivity(inti);




//                            Intent intent = new Intent(LoginActivity.this,testActivity.class);
//                            startActivity(intent);

//                        }else {
//                            Toast.makeText(LoginActivity.this,"Password that you entered is WRONG",Toast.LENGTH_SHORT).show();
//
//                        }

                    }


                else {
                    Toast.makeText(LoginActivity.this,"User by this "+ phone+" does not exits",Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                loadingBar.setTitle("Problem Hocche");



            }
        });
    }
}
