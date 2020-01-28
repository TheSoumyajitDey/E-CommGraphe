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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class RegisterActivity extends AppCompatActivity {

    private Button CreateAccountButton;
    private EditText inputName ,inputPassword,inputPhoneNumber;
    private ProgressDialog loadingbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        CreateAccountButton = (Button) findViewById(R.id.createAccount);
        inputName = (EditText) findViewById(R.id.registration_your_name);
        inputPassword = (EditText) findViewById(R.id.registraion_password);
        inputPhoneNumber = (EditText) findViewById(R.id.registration_phone_Number);
        loadingbar = new ProgressDialog(this);



        CreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createAccountButtonPressed();

            }
        });
    }

    private void createAccountButtonPressed() {
        String name = inputName.getText().toString();
        String phoneNumber = inputPhoneNumber.getText().toString();
        String password = inputPassword.getText().toString();


        if(TextUtils.isEmpty(name)){
            Toast.makeText(this,"Please Enter Your Name",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(phoneNumber)){
            Toast.makeText(this,"Please Enter Your Phone Number",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please Enter Your Password",Toast.LENGTH_SHORT).show();
        }
       else {
            loadingbar.setTitle("Welcome ");
            loadingbar.setMessage("Please wait, We are talking with the database !");
            loadingbar.setCanceledOnTouchOutside(false);
            loadingbar.show();
            ValidatePhoneNumber(name,phoneNumber,password);

        }


    }

    private void ValidatePhoneNumber(final String name, final String phoneNumber, final String password) {

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!(dataSnapshot.child("Users").child(phoneNumber).exists())){

                    HashMap<String,Object> userdataMap = new HashMap<>();
                    userdataMap.put("phone",phoneNumber);
                    userdataMap.put("password",password);
                    userdataMap.put("name",name);

                    RootRef.child("Users").child(phoneNumber).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful()){
                                        Toast.makeText(RegisterActivity.this,"Your Account Has Been Registered,Welcome to the Family!",Toast.LENGTH_SHORT).show();
                                        loadingbar.dismiss();
//                                        Intent inti = new Intent(RegisterActivity.this,testActivity.class);
//                                           startActivity(inti);

                                        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                                        startActivity(intent);
                                    }else {
                                        loadingbar.dismiss();
                                        Toast.makeText(RegisterActivity.this,"Network Error: Please Try Again After SomeTimes......",Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });



                }
                else {
                    Toast.makeText(RegisterActivity.this,"This "+phoneNumber+" Already Registered!",Toast.LENGTH_SHORT).show();
                    loadingbar.dismiss();
                    Toast.makeText(RegisterActivity.this,"Try Again With Another Phone!",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                    startActivity(intent);



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(RegisterActivity.this,"its not working",Toast.LENGTH_SHORT).show();



            }
        });




    }
}
