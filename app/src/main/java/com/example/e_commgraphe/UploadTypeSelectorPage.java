package com.example.e_commgraphe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

    }
}
