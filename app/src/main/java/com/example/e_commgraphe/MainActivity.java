package com.example.e_commgraphe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.e_commgraphe.Prevalent.prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    private Button joinNowButton,loginButton;
    private ProgressDialog loadingbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        joinNowButton = (Button)findViewById(R.id.main_join_now_button);
        loginButton = (Button)findViewById(R.id.main_login_button);
        loadingbar = new ProgressDialog(this);


        Paper.init(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        joinNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        String UserPhoneKey = Paper.book().read(prevalent.UserPhoneKey);
        String UserKeyPassword = Paper.book().read(prevalent.UserPasswordKey);

        if(UserPhoneKey != "" && UserKeyPassword != ""){
            if(!TextUtils.isEmpty(UserPhoneKey)&& !TextUtils.isEmpty(UserKeyPassword)){

                AllowAccess(UserPhoneKey,UserKeyPassword);

                loadingbar.setTitle("Welcome Your Already logged in ");
                loadingbar.setMessage("Please wait.....we are processing and retriving your data!");
                loadingbar.setCanceledOnTouchOutside(false);
                loadingbar.show();

            }
        }


    }

    private void AllowAccess(final String phone, final String password) {

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference().child("Users").child(phone);

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                if((dataSnapshot.child("phone").getValue().toString()).equals(phone)){
                    if((dataSnapshot.child("password").getValue().toString()).equals(password)){

                        Intent inti = new Intent(MainActivity.this,HomeActivity.class);
                        startActivity(inti);

                        Toast.makeText(MainActivity.this,"Welcome back ! Logged in Successfully",Toast.LENGTH_SHORT).show();
                        loadingbar.dismiss();


                    }else {

                        Toast.makeText(MainActivity.this,"Sorry Your password you enterered is WRONG!",Toast.LENGTH_SHORT).show();
                        loadingbar.dismiss();

                    }


                }


                else {
                    Toast.makeText(MainActivity.this,"User by this "+ phone+" does not exits",Toast.LENGTH_SHORT).show();
                    loadingbar.dismiss();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {





            }
        });
    }
}
