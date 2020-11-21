package com.beri.beriid;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HistoryTransaction extends AppCompatActivity {

    ListView listView;
    String m_yayasanName[] = {"Yayasan A", "Yayasan B", "Yayasan C", "Yayasan D"};
    String m_donationDetail[] = {"Masker 100", "Baju 20", "Tas 10", "Selimut 50"};


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_transaction);

        listView = findViewById(R.id.listView);

        HistoryTransactionAdapter adapter = new HistoryTransactionAdapter(this, m_yayasanName, m_donationDetail);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0) {
                    Toast.makeText(HistoryTransaction.this, "Yayasan A Desc", Toast.LENGTH_SHORT).show();
                }
                if(position == 0) {
                    Toast.makeText(HistoryTransaction.this, "Yayasan B Desc", Toast.LENGTH_SHORT).show();
                }
                if(position == 0) {
                    Toast.makeText(HistoryTransaction.this, "Yayasan C Desc", Toast.LENGTH_SHORT).show();
                }
                if(position == 0) {
                    Toast.makeText(HistoryTransaction.this, "Yayasan D Desc", Toast.LENGTH_SHORT).show();
                }
                if(position == 0) {
                    Toast.makeText(HistoryTransaction.this, "Yayasan E Desc", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    class HistoryTransactionAdapter extends ArrayAdapter<String> {
        Context context;
        String r_yayasanName[];
        String r_donationDetail[];

        HistoryTransactionAdapter (Context c, String yayasanName[], String donationDetail[]) {
            super(c, R.layout.ht_row, R.id.nama_yayasan, yayasanName);
            this.context = c;
            this.r_yayasanName = yayasanName;
            this.r_donationDetail = donationDetail;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.ht_row, parent, false);
            TextView myYayasanName = row.findViewById(R.id.nama_yayasan);
            TextView myDonationDetail = row.findViewById(R.id.detail_donasi);

            myYayasanName.setText(r_yayasanName[position]);
            myDonationDetail.setText(r_donationDetail[position]);

            return row;
        }
    }
}
