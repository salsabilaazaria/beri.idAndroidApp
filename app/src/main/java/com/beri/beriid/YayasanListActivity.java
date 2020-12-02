package com.beri.beriid;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.beri.beriid.Adapters.YayasanAdapter;
import com.beri.beriid.Model.Foundation;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class YayasanListActivity extends AppCompatActivity {


    DatabaseHelper db;
    ListView YayasanLV;
    ArrayList<Foundation> arrayList;
    YayasanAdapter yayasanAdapter;
    int user_id;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yayasanlist);

        YayasanLV = (ListView)findViewById(R.id.yayasanlistView);
        db = new DatabaseHelper(this);

        Intent intent = getIntent();
        user_id = intent.getIntExtra("user_id", 0);

        loadDataInListView();

        arrayList = db.getAllYayasanData();
        yayasanAdapter = new YayasanAdapter(this, arrayList);
        YayasanLV.setAdapter(yayasanAdapter);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_home:
                        Intent intent = new Intent(YayasanListActivity.this, HomePageActivity.class);
                        intent.putExtra("user_id",user_id);
                        startActivity(intent);
                        return true;

                    case R.id.nav_profie:
                        Intent i = new Intent(YayasanListActivity.this, ProfilePageActivity.class);
                        i.putExtra("userid",user_id);
                        startActivity(i);
                        return true;

                }
                return false;
            }

        });

        YayasanLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(),YayasanDetailActivity.class);
                intent.putExtra("user_id", user_id);
                intent.putExtra("id", arrayList.get(position).getId());
                intent.putExtra("name", arrayList.get(position).getName().toString());
                intent.putExtra("desc", arrayList.get(position).getDescription().toString());
                intent.putExtra("address", arrayList.get(position).getAddress().toString());
                startActivity(intent);

            }
        });


    }



    private void loadDataInListView() {
        arrayList = db.getAllYayasanData();
        yayasanAdapter = new YayasanAdapter(this, arrayList);
        YayasanLV.setAdapter(yayasanAdapter);

        yayasanAdapter.notifyDataSetChanged();

    }


}
