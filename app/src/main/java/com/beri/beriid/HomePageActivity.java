package com.beri.beriid;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomePageActivity extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap map;
    LinearLayout seefoundation;
    int user_id;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        //bottom navigation
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {

            }
        });

        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public void onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_profie:
                        startActivity(new Intent(getApplicationContext(), ProfilePageActivity.class));
                        finish();
                        overridePendingTransition(0, 0);
                        return;
                    case R.id.nav_home:
                }
            }
        });

        user_id = getIntent().getIntExtra("user_id", 0);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        seefoundation = (LinearLayout)findViewById(R.id.seefoundation);

        seefoundation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seefoundationpage();
            }
        });

//        mydb = new DatabaseHelper(HomePageActivity.this);
//        id = new ArrayList<>();
//        name = new ArrayList<>();
//        description = new ArrayList<>();
//        address = new ArrayList<>();
//
//        recyclerView= (RecyclerView)findViewById(R.id.recyclerviewyayasan);
//
//        storedatainarrays();
//
//        homeYayasanAdapter = new HomeYayasanAdapter(HomePageActivity.this, id, name,description,address);
//        recyclerView.setAdapter(homeYayasanAdapter);
//
//
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
//        recyclerView.setLayoutManager(gridLayoutManager);


    }

    public void seefoundationpage(){
        Intent i = new Intent(this,YayasanListActivity.class);
        i.putExtra("user_id", user_id);
        startActivity(i);
    }

//    void storedatainarrays(){
//        Cursor cursor= mydb.readYayasanData();
//        if (cursor.getCount()==0){
//            Toast.makeText(this,"No Data", Toast.LENGTH_SHORT).show();
//
//        }
//        else{
//            while(cursor.moveToNext()){
//                id.add(cursor.getString(0));
//                name.add(cursor.getString(1));
//                description.add(cursor.getString(2));
//                address.add(cursor.getString(3));
//            }
//        }
//    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;
        LatLng binus = new LatLng(-6.224690968836054, 106.65027479999998);
        map.addMarker(new MarkerOptions().position(binus).title("BINUS University"));
        map.moveCamera(CameraUpdateFactory.newLatLng(binus));

    }

}
