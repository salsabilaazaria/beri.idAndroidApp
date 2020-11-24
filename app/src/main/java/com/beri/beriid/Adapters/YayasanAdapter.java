package com.beri.beriid.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.beri.beriid.Model.Foundation;
import com.beri.beriid.R;

import java.util.ArrayList;

public class YayasanAdapter extends BaseAdapter   {

    Context context;
    ArrayList<Foundation> arrayList;
    public YayasanAdapter(Context context, ArrayList<Foundation> arrayList){
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
            convertView = inflater.inflate(R.layout.yayasanlistviewrow,null);
            TextView yayasantextname = (TextView)convertView.findViewById(R.id.yayasantextname);
            TextView yayasantextdesc = (TextView)convertView.findViewById(R.id.yayasantextdesc);

            Foundation foundation = arrayList.get(position);
            yayasantextname.setText(foundation.getName());
            yayasantextdesc.setText(foundation.getDescription());




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
