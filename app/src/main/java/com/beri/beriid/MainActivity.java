package com.beri.beriid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DatabaseHelper db = new DatabaseHelper(this);

//                db.addData("Sasana Tresna Werdha Ciracas","Nursing Home","Jl. Raya Ciracas No.60, RT.4/RW.11, Klp. Dua Wetan, Ciracas, Kota Jakarta Timur");
//                db.addData("Elderly Nursing Kasih Ayah Bunda","Nursing Home","JL. Ternate Raya, Karawaci, Perumnas III, Bencongan Indah, Kec. Tangerang");
//                db.addData("Marfati Nursing Home","Nursing Home","Jl. Dr. Sitanala, 85, Tangerang, 166, RT.001/RW.003, Mekarsari, Neglasari, Tangerang City");
//                db.addData("Kidung Salomo Agape Elderly Home","Nursing Home","Jl. Utama I No.83, Pd. Karya, Kec. Pd. Aren, Kota Tangerang Selatan");
//                db.addData("Elderly Nursing Jara Mara Pati","Nursing Home","JL. Seririt - Singaraja, Seririt, Buleleng, Banjar, Kec. Banjar, Kabupaten Buleleng");
//                db.addData("Baitunnisa Foundation", "Orphanage", "Jl. Raya H. Abdullah No.30, Pakulonan Bar., Kec. Klp. Dua, Tangerang");
//                db.addData("Al Mubaroh Foundation", "Orphanage", "Jl. KH Hasyim Ashari Rt.05, Gg. Jambu No.2, RT.006/RW.005, Buaran Indah, Kec. Tangerang, Kota Tangerang");
//                db.addData("Pelangi Foundation", "Orphanage", "Cigondewah Hilir, Margaasih, Bandung");
//                db.addData("Sayap Ibu Foundation", "Orphanage", "Jl. Graha Raya Bintaro No.33B, Pd. Kacang Bar., Kec. Pd. Aren, Kota Tangerang Selatan");
//                db.addData("Rumah Yatim", "Orphanage", "Jl. Beringin Raya No.94, RT.002/RW.008, Karawaci Baru, Kec. Karawaci, Kota Tangerang");



        Toast.makeText(this,"Value Saved", Toast.LENGTH_LONG).show();

    }


    public void gantikelogin(View view) {
        Intent intent = new Intent(MainActivity.this, Login.class);
        startActivity(intent);
    }
}