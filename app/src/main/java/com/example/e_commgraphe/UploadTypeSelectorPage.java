package com.example.e_commgraphe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class UploadTypeSelectorPage extends AppCompatActivity {

    //variable inintialztion for all the image there in the list view
    private ImageView shoes,glasses,speakers;
    private ImageView phones,laptops,clothes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_type_selector_page);

        //all the connection made with the XML imageview with the Java file is over
        shoes = (ImageView) findViewById(R.id.shoes);
        glasses =(ImageView) findViewById(R.id.glasses);
        speakers = (ImageView) findViewById(R.id.speakers);
        phones = (ImageView) findViewById(R.id.phone);
        laptops = (ImageView) findViewById(R.id.laptops);
        clothes = (ImageView)findViewById(R.id.dresses);

        //for shoes click on listner and passing the catagory type and passing the variables using the intent one
        shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UploadTypeSelectorPage.this,AdminPowerToAddNewProduct.class);
                intent.putExtra("catagory ","Shoes");
                startActivity(intent);
            }
        });

        //for glasses click on Listner and passing the catagory type and passing the variables using the intent one
        glasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UploadTypeSelectorPage.this,AdminPowerToAddNewProduct.class);
                intent.putExtra("catagory ","glasses");
                startActivity(intent);
            }
        });

        //for speaker click on Listner and passing the catagory type and passing the variables using the intent one

        speakers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UploadTypeSelectorPage.this,AdminPowerToAddNewProduct.class);
                intent.putExtra("catagory ","speakers");
                startActivity(intent);
            }
        });

        //for phones click on Listner and passing the catagory type and passing the variables using the intent one

        phones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UploadTypeSelectorPage.this,AdminPowerToAddNewProduct.class);
                intent.putExtra("catagory ","phones");
                startActivity(intent);
            }
        });

        //for laptops click on Listner and passing the catagory type and passing the variables using the intent one

        laptops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UploadTypeSelectorPage.this,AdminPowerToAddNewProduct.class);
                intent.putExtra("catagory ","laptops");
                startActivity(intent);
            }
        });

        //for clothers click on Listner and passing the catagory type and passing the variables using the intent one

        clothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UploadTypeSelectorPage.this,AdminPowerToAddNewProduct.class);
                intent.putExtra("catagory ","clothes");
                startActivity(intent);
            }
        });





    }
}
