package com.beri.beriid;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.beri.beriid.Model.User;

import java.util.ArrayList;

public class ProfilePageActivity extends AppCompatActivity {
    TextView profileusername;
    TextView profilenik;
    TextView profileemail;
    TextView profileaddress;

    com.beri.beriid.DatabaseHelper db;
    String userid;
    ArrayList<User> userArrayList;

    private TextView transactionhistorybutton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profilepage);

        profileusername = findViewById(R.id.profile_username);
        profilenik = findViewById(R.id.profile_nik);
        profileemail = findViewById(R.id.profile_email);
        profileaddress = findViewById(R.id.profile_address);

//        Intent intent  = getIntent();
//
//        userid = intent.getStringExtra("userid");


        db = new com.beri.beriid.DatabaseHelper(this);

        userArrayList = db.getAllUserData();

        profileusername.setText(userArrayList.get(0).getUsername());
        profileemail.setText(userArrayList.get(0).getEmail());
        profileaddress.setText(userArrayList.get(0).getAddress());
        profilenik.setText(userArrayList.get(0).getNik());

        //Transaction History Button
        transactionhistorybutton = (TextView) findViewById(R.id.profile_transactionhistorybtn);
        transactionhistorybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opentransactionhistorypage();
            }

        });
    }

    public void opentransactionhistorypage(){
        Intent i = new Intent(this,HomePageActivity.class);
        startActivity(i);
    }


}
