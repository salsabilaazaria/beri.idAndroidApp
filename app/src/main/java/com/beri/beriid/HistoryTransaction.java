package com.beri.beriid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.beri.beriid.Adapters.HistoryAdapter;
import com.beri.beriid.Model.History;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class HistoryTransaction extends AppCompatActivity {

    DatabaseHelper db;
    ListView HistoryLV;
    ArrayList<History> arrayList;
    HistoryAdapter historyAdapter;

    Bitmap b;
    ByteArrayOutputStream bs;
//    private Object AdapterView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_transaction);

        HistoryLV = (ListView) findViewById(R.id.listViewHistory);
        db = new DatabaseHelper(this);
        arrayList = db.viewHistory();

        loadDataInListView();

        historyAdapter = new HistoryAdapter(this, arrayList);
        HistoryLV.setAdapter(historyAdapter);

        HistoryLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), HistoryTransactionDetail.class);

                b = arrayList.get(position).getDonation_image();
                ByteArrayOutputStream bStream = new ByteArrayOutputStream();
                b.compress(Bitmap.CompressFormat.PNG, 100, bStream);
                byte[] byteArray = bStream.toByteArray();


                intent.putExtra("id", arrayList.get(position).getDonation_id());
                intent.putExtra("user_id", arrayList.get(position).getUser_id());
                intent.putExtra("foundation_id", arrayList.get(position).getFoundation_id());
                intent.putExtra("quantity", arrayList.get(position).getQuantity());
                intent.putExtra("name", arrayList.get(position).getDonation_name());
                intent.putExtra("description", arrayList.get(position).getDonation_description());
                intent.putExtra("image", byteArray);
                intent.putExtra("foundation_name", arrayList.get(position).getFoundation_name());
                intent.putExtra("foundation_description", arrayList.get(position).getFoundation_description());
                intent.putExtra("foundation_address", arrayList.get(position).getFoundation_address());

                startActivity(intent);
            }
        });
    }

//        listView = findViewById(R.id.listViewHistory);
//
//        HistoryTransactionAdapter adapter = new HistoryTransactionAdapter(this, m_yayasanName, m_donationDetail);
//        listView.setAdapter(adapter);

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if(position == 0) {
//                    Toast.makeText(HistoryTransaction.this, "Yayasan A Desc", Toast.LENGTH_SHORT).show();
//                }
//                if(position == 0) {
//                    Toast.makeText(HistoryTransaction.this, "Yayasan B Desc", Toast.LENGTH_SHORT).show();
//                }
//                if(position == 0) {
//                    Toast.makeText(HistoryTransaction.this, "Yayasan C Desc", Toast.LENGTH_SHORT).show();
//                }
//                if(position == 0) {
//                    Toast.makeText(HistoryTransaction.this, "Yayasan D Desc", Toast.LENGTH_SHORT).show();
//                }
//                if(position == 0) {
//                    Toast.makeText(HistoryTransaction.this, "Yayasan E Desc", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }

    private void loadDataInListView() {
        arrayList = db.viewHistory();
        historyAdapter = new HistoryAdapter(this, arrayList);
        HistoryLV.setAdapter(historyAdapter);

        historyAdapter.notifyDataSetChanged();
    }

//    class HistoryTransactionAdapter extends ArrayAdapter<String> {
//        Context context;
//        String r_yayasanName[];
//        String r_donationDetail[];
//
//        HistoryTransactionAdapter (Context c, String yayasanName[], String donationDetail[]) {
//            super(c, R.layout.ht_row, R.id.nama_yayasan, yayasanName);
//            this.context = c;
//            this.r_yayasanName = yayasanName;
//            this.r_donationDetail = donationDetail;
//        }
//
//        @NonNull
//        @Override
//        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View row = layoutInflater.inflate(R.layout.ht_row, parent, false);
//            TextView myYayasanName = row.findViewById(R.id.nama_yayasan);
//            TextView myDonationDetail = row.findViewById(R.id.detail_donasi);
//
//            myYayasanName.setText(r_yayasanName[position]);
//            myDonationDetail.setText(r_donationDetail[position]);
//
//            return row;
//        }
//    }
}
