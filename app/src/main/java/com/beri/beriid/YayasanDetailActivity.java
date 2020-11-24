package com.beri.beriid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class YayasanDetailActivity extends AppCompatActivity {

    TextView yayasandetailtextname;
    TextView yayasandetailtextdesc;
    TextView yayasandetailtextaddress;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yayasandetail);

        yayasandetailtextname = findViewById(R.id.yayasandetailtextname);
        yayasandetailtextdesc = findViewById(R.id.yayasandetailtextdesc);
        yayasandetailtextaddress = findViewById(R.id.yayasandetailtextaddress);

        Intent intent = getIntent();

        yayasandetailtextname.setText(intent.getStringExtra("name"));
        yayasandetailtextdesc.setText(intent.getStringExtra("desc"));
        yayasandetailtextaddress.setText(intent.getStringExtra("address"));




    }


}
