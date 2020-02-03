package com.example.e_commgraphe;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AdminPowerToAddNewProduct extends AppCompatActivity {

    //variable to be created when images are pressed in the selection pannel it ... as from the previous Java Files and XML files we could understand that
    //all the images when tapped goes to the same uploadabel page but we in database we need to make sure that the files should be divided by catagory
    //so we are attaching the catagory name tag to itself and to make it identfyable !!

    private String catagoryName = "if this is displayed then value is not passing ";
    private Button addNewProductImage;
    private EditText productName;
    private EditText productDescription;
    private EditText productPrice;
    private String Description,Name,Price,saveCurrentDate,saveCurrentTime;
    private ImageView taptoOpen;
    private static final int GalleryPick = 1;
    private Uri imageUri;
    private String productRandomKey,downloadImageURL;
    private StorageReference ProductImageRef;
    private DatabaseReference ProductsRef;
    private ProgressDialog loadingBar;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_power_to_add_new_product);
       //this is just a toast just to roast are choro its just to see whether the page is loading properly or not !
        //this is just a test to work and and to work and it should work am just blabbing
        //will be adding features where the admin will have the capibility of uploading images directly from the smartphone and the images would be directly uploaded to the Firebase
        //Database and Firebase Cloud Database !

        //this comment taking the tag and feeding it to the catagory name
        catagoryName = getIntent().getStringExtra("catagory ");
        ProductImageRef = FirebaseStorage.getInstance().getReference().child("Product Images");
        ProductsRef = FirebaseDatabase.getInstance().getReference().child("Products");


        //connection with the XML and its respective JAVA file
        addNewProductImage = (Button) findViewById(R.id.uploadImageButton);
        productName = (EditText)findViewById(R.id.productName);
        productDescription = (EditText)findViewById(R.id.productDescription);
        productPrice = (EditText) findViewById(R.id.productPrice);
        taptoOpen = (ImageView) findViewById(R.id.select_Product_Image);

        loadingBar = new ProgressDialog(this);


        taptoOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGalary();
            }
        });

        addNewProductImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateProductData();
            }
        });


        //just making a toast
        Toast.makeText(AdminPowerToAddNewProduct.this,"Superman "+ catagoryName,Toast.LENGTH_SHORT).show();

    }

    private void ValidateProductData() {
        Name = productName.getText().toString();
        Description = productDescription.getText().toString();
        Price = productPrice.getText().toString();

        if(imageUri == null){
            Toast.makeText(AdminPowerToAddNewProduct.this,"You Have to Add a Product Image",Toast.LENGTH_SHORT).show();

        }

        else if (TextUtils.isEmpty(Name)){
            Toast.makeText(AdminPowerToAddNewProduct.this,"Name Of the Product Has to be Added",Toast.LENGTH_SHORT).show();

        }
        else if (TextUtils.isEmpty(Description)){
            Toast.makeText(AdminPowerToAddNewProduct.this,"Description Of the Product can not be Empty ",Toast.LENGTH_SHORT).show();

        }
        else if (TextUtils.isEmpty(Price)){
            Toast.makeText(AdminPowerToAddNewProduct.this,"Price of the product can not be empty",Toast.LENGTH_SHORT).show();

        }
        else {
            StoreProductInformation();
        }




    }

    private void StoreProductInformation() {


        loadingBar.setTitle("Adding New Product ");
        loadingBar.setMessage("Admin ... we are adding your product ! just PEACE MARO!");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentData = new SimpleDateFormat("MMM dd,yyyy");
        saveCurrentDate = currentData.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        productRandomKey = saveCurrentDate+saveCurrentTime;

        final StorageReference filePath = ProductImageRef.child(imageUri.getLastPathSegment() + productRandomKey+".jpg");

        final UploadTask uploadTask = filePath.putFile(imageUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                String message = e.toString();

                Toast.makeText(AdminPowerToAddNewProduct.this,"Error: "+message,Toast.LENGTH_SHORT).show();

                loadingBar.dismiss();


            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(AdminPowerToAddNewProduct.this,"Image Uploaded Successfully! Yaha Admin",Toast.LENGTH_SHORT).show();

                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if(!task.isSuccessful()){
                            throw task.getException();

                        }

                        downloadImageURL = filePath.getDownloadUrl().toString();


                        return filePath.getDownloadUrl();

                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(AdminPowerToAddNewProduct.this,"Getting Product Image Has Been Successfully uploaded to the Database ",Toast.LENGTH_SHORT).show();
                            SaveProductInfoToTheDatabase();
                        }

                    }
                });
            }
        });





    }

    private void SaveProductInfoToTheDatabase() {


        HashMap<String,Object> productMap = new HashMap<>();
        productMap.put("pid",productRandomKey);
        productMap.put("date",saveCurrentDate);
        productMap.put("time",saveCurrentTime);
        productMap.put("description",Description);
        productMap.put("image",downloadImageURL);
        productMap.put("catagory",catagoryName);
        productMap.put("price",Price);
        productMap.put("name",Name);

        ProductsRef.child(productRandomKey).updateChildren(productMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                            downloadImageURL = task.getResult().toString();
                            Intent inti = new Intent(AdminPowerToAddNewProduct.this,UploadTypeSelectorPage.class);
                            startActivity(inti);
                            loadingBar.dismiss();

                            Toast.makeText(AdminPowerToAddNewProduct.this,"Product Has Been Added Successfully ",Toast.LENGTH_SHORT).show();

                        }
                        else {
                            loadingBar.dismiss();

                            String errorMessage = task.getException().toString();
                            Toast.makeText(AdminPowerToAddNewProduct.this,"Error: "+ errorMessage,Toast.LENGTH_SHORT).show();

                        }

                    }
                });


    }


    private void OpenGalary() {
        Intent galaryIntent = new Intent();
        galaryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galaryIntent.setType("image/*");
        startActivityForResult(galaryIntent,GalleryPick);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GalleryPick && resultCode == RESULT_OK && data!= null){
            imageUri = data.getData();
            taptoOpen.setImageURI(imageUri);



        }
    }
}
