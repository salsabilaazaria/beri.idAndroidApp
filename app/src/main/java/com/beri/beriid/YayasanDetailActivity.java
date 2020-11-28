package com.beri.beriid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
    }
}
