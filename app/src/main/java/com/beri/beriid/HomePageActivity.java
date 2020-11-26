package com.beri.beriid;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.beri.beriid.Adapters.HomeYayasanAdapter;
import com.beri.beriid.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class HomePageActivity extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap map;
    DatabaseHelper mydb;
    ArrayList<String> id, name, description, address;
    HomeYayasanAdapter homeYayasanAdapter;
    RecyclerView recyclerView;


    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mydb = new DatabaseHelper(HomePageActivity.this);
        id = new ArrayList<>();
        name = new ArrayList<>();
        description = new ArrayList<>();
        address = new ArrayList<>();

        recyclerView= (RecyclerView)findViewById(R.id.recyclerviewyayasan);

        storedatainarrays();

        homeYayasanAdapter = new HomeYayasanAdapter(HomePageActivity.this, id, name,description,address);
        recyclerView.setAdapter(homeYayasanAdapter);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);



    }

    void storedatainarrays(){
        Cursor cursor= mydb.readYayasanData();
        if (cursor.getCount()==0){
            Toast.makeText(this,"No Data", Toast.LENGTH_SHORT).show();

        }
        else{
            while(cursor.moveToNext()){
                id.add(cursor.getString(0));
                name.add(cursor.getString(1));
                description.add(cursor.getString(2));
                address.add(cursor.getString(3));
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;
        LatLng binus = new LatLng(-6.224690968836054, 106.65027479999998);
        map.addMarker(new MarkerOptions().position(binus).title("BINUS University"));
        map.moveCamera(CameraUpdateFactory.newLatLng(binus));

    }




}
