package com.beri.beriid;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class YayasanDetailActivity extends AppCompatActivity {

    TextView yayasandetailtextname;
    TextView yayasandetailtextdesc;
    TextView yayasandetailtextaddress;
    int user_id, f_id;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yayasandetail);

        yayasandetailtextname = findViewById(R.id.yayasandetailtextname);
        yayasandetailtextdesc = findViewById(R.id.yayasandetailtextdesc);
        yayasandetailtextaddress = findViewById(R.id.yayasandetailtextaddress);

        Intent intent = getIntent();

        f_id = intent.getIntExtra("id", 0);
        user_id = intent.getIntExtra("user_id", 0);

        yayasandetailtextname.setText(intent.getStringExtra("name"));
        yayasandetailtextdesc.setText(intent.getStringExtra("desc"));
        yayasandetailtextaddress.setText(intent.getStringExtra("address"));

        Button button = findViewById(R.id.donateButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), DonateActivity.class);
                i.putExtra("user_id", user_id);
                i.putExtra("f_id", f_id);
                startActivity(i);
            }
        });

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_home:
                        Intent intent = new Intent(YayasanDetailActivity.this, HomePageActivity.class);
                        intent.putExtra("user_id",user_id);
                        startActivity(intent);
                        return true;

                    case R.id.nav_profie:
                        Intent i = new Intent(YayasanDetailActivity.this, ProfilePageActivity.class);
                        i.putExtra("userid",user_id);
                        startActivity(i);
                        return true;

                }
                return false;
            }

        });
    }
}
