package com.beri.beriid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.beri.beriid.Adapters.YayasanAdapter;
import com.beri.beriid.Model.Foundation;

import java.util.ArrayList;

public class YayasanListActivity extends AppCompatActivity {


    DatabaseHelper db;
    ListView YayasanLV;
    ArrayList<Foundation> arrayList;
    YayasanAdapter yayasanAdapter;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yayasanlist);

        YayasanLV = (ListView)findViewById(R.id.yayasanlistView);
        db = new DatabaseHelper(this);

        loadDataInListView();

        arrayList = db.getAllYayasanData();
        yayasanAdapter = new YayasanAdapter(this, arrayList);
        YayasanLV.setAdapter(yayasanAdapter);

        YayasanLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent intent = new Intent(getApplicationContext(),YayasanDetailActivity.class);
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
