package com.example.e_commgraphe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class AdminPowerToAddNewProduct extends AppCompatActivity {

    //variable to be created when images are pressed in the selection pannel it ... as from the previous Java Files and XML files we could understand that
    //all the images when tapped goes to the same uploadabel page but we in database we need to make sure that the files should be divided by catagory
    //so we are attaching the catagory name tag to itself and to make it identfyable !!

    private String catagoryName = "if this is displayed then value is not passing ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_power_to_add_new_product);
       //this is just a toast just to roast are choro its just to see whether the page is loading properly or not !
        //this is just a test to work and and to work and it should work am just blabbing
        //will be adding features where the admin will have the capibility of uploading images directly from the smartphone and the images would be directly uploaded to the Firebase
        //Database and Firebase Cloud Database !


        //system crashing when value is taken back from the previous JAVA file and and trying to toast the selected variable
        catagoryName = getIntent().getExtras().get("catagory").toString();


        Toast.makeText(AdminPowerToAddNewProduct.this,"Superman ",Toast.LENGTH_SHORT).show();







    }
}
