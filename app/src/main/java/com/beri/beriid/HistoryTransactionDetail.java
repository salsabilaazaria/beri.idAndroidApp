package com.beri.beriid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HistoryTransactionDetail extends AppCompatActivity {
    TextView donationFoundationName;
    TextView donationFoundationDescription;
    TextView donationFoundationAddress;
//    TextView donationImage;
    ImageView donationImage;
    TextView donationName;
    TextView donationQuantity;
    TextView donationDescription;
    Bitmap bmp;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_transaction_detail);

        donationFoundationName = findViewById(R.id.nama_yayasan);
        donationFoundationDescription = findViewById(R.id.deskripsi_yayasan);
        donationFoundationAddress = findViewById(R.id.alamat_yayasan);
        donationImage = findViewById(R.id.itempic);
//    ImageView donationImage;
        donationName = findViewById(R.id.itemname);
        donationQuantity = findViewById(R.id.itemquantity);
        donationDescription = findViewById(R.id.itemdesc);

        Intent intent = getIntent();
        byte[] byteArray = getIntent().getByteArrayExtra("image");
        bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
//        Bitmap b = intent.getParcelableExtra("image");
//        Bundle extras = intent.getExtras();
//        byte[] ba = image;

        donationFoundationName.setText(intent.getStringExtra("foundation_name"));
        donationFoundationDescription.setText(intent.getStringExtra("foundation_description"));
        donationFoundationAddress.setText(intent.getStringExtra("foundation_address"));
//        donationImage.setText(intent.getStringExtra("image"));
//    ImageView donationImage;

        int qty = intent.getIntExtra("quantity",1);
        String qtystring = Integer.toString(qty);
        donationImage.setImageBitmap(bmp);
        donationName.setText(intent.getStringExtra("name"));
        donationQuantity.setText(intent.getStringExtra("description"));
        donationDescription.setText(qtystring);
    }
}
