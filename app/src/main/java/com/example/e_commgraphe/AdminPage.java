package com.example.e_commgraphe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.e_commgraphe.Prevalent.prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

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

                AdminLoginUsers();



            }
        });









    }


    private void AdminLoginUsers(){
        String phone = adminPhoneNumber.getText().toString();
        String password =adminPassword.getText().toString();

        if(TextUtils.isEmpty(phone)){
            Toast.makeText(AdminPage.this,"Please Enter Your Valid Phone Number",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password)){
            Toast.makeText(AdminPage.this,"Please Enter Your Valid Password",Toast.LENGTH_SHORT).show();

        }else {
            loadingBar.setTitle("Admin Login Account");
            loadingBar.setMessage("We are arranging things for you! Just a Moment! Admin Bro ");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            AdminAllowAccessToAccount(phone,password);
        }

    }



    private void AdminAllowAccessToAccount(final String phone, final String password) {


        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference().child("Admin").child(phone);

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                if((dataSnapshot.child("phone").getValue().toString()).equals(phone)){
                    if((dataSnapshot.child("password").getValue().toString()).equals(password)){

                        Intent inti = new Intent(AdminPage.this,UploadTypeSelectorPage.class);
                        startActivity(inti);

                        Toast.makeText(AdminPage.this,"Hi Admin ! Welcome ",Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();


                    }else {

                        Toast.makeText(AdminPage.this,"Sorry Admin Your password you enterered is WRONG!",Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();

                    }


                }


                else {
                    Toast.makeText(AdminPage.this,"Admin by this "+ phone+" does not exits",Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                loadingBar.setTitle("Admin Problem Hocche");



            }
        });
    }
}
