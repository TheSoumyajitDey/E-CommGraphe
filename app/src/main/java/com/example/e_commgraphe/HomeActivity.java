package com.example.e_commgraphe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.e_commgraphe.model.ImageRetrive;
import com.example.e_commgraphe.model.ViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import io.paperdb.Paper;

public class HomeActivity extends AppCompatActivity {

    private Button logoutbutton;
    RecyclerView mRecylerView;
    FirebaseDatabase mfirebaseDatabase;
    DatabaseReference mRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


//



        logoutbutton = (Button)findViewById(R.id.logoutbutton);
        logoutbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Paper.book().destroy();
                Intent intent = new Intent(HomeActivity.this,MainActivity.class);
                startActivity(intent);



                Toast.makeText(HomeActivity.this,"Bye Bandhu Bye",Toast.LENGTH_SHORT).show();


            }
        });
    }


}
