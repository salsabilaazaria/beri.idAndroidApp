package com.beri.beriid.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.beri.beriid.Model.History;
import com.beri.beriid.R;

import java.util.ArrayList;

public class HistoryAdapter extends BaseAdapter   {

    Context context;
    ArrayList<History> arrayList;
    public HistoryAdapter(Context context, ArrayList<History> arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.ht_row,null);
        TextView namayayasan = (TextView)convertView.findViewById(R.id.nama_yayasan);
        TextView detaildonasi = (TextView)convertView.findViewById(R.id.detail_donasi);

        History history = arrayList.get(position);
        namayayasan.setText(history.getFoundation_name());
        detaildonasi.setText(history.getDonation_name());

        return convertView;
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }



    @Override
    public int getCount() {
        return this.arrayList.size();
    }
}