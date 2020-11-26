package com.beri.beriid.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.beri.beriid.R;

import java.util.ArrayList;

public class HomeYayasanAdapter extends RecyclerView.Adapter<HomeYayasanAdapter.MyViewHolder> {

  private Context context;
  private ArrayList id, name, description, address;


   public HomeYayasanAdapter(Context context, ArrayList id, ArrayList name, ArrayList description, ArrayList address){
       this.context = context;
       this.id = id;
       this.name=name;
       this.description=description;
       this.address=address;
   }


    @NonNull
    @Override
    public HomeYayasanAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.yayasarvdetail, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeYayasanAdapter.MyViewHolder holder, int position) {
        holder.nameyayasantext.setText(String.valueOf(name.get(position)));
        holder.descriptionyayasantext.setText(String.valueOf(description.get(position)));
    }

    @Override
    public int getItemCount() {
        return 4;
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

       TextView nameyayasantext, descriptionyayasantext;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameyayasantext = itemView.findViewById(R.id.homeyayasannametextview);
            descriptionyayasantext = itemView.findViewById(R.id.homeyayasandescriptiontextview);
        }
    }
}
