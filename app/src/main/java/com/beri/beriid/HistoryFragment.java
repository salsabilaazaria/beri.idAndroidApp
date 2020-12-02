package com.beri.beriid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.beri.beriid.Adapters.HistoryAdapter;
import com.beri.beriid.Model.History;
import com.beri.beriid.Model.User;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HistoryFragment() {
        // Required empty public constructor
    }

    DatabaseHelper db;
    ListView HistoryLV;
    ArrayList<History> arrayList;
    HistoryAdapter historyAdapter;

    Bitmap b;
    ByteArrayOutputStream bs;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HistoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HistoryFragment newInstance(String param1, String param2) {
        HistoryFragment fragment = new HistoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final ProfilePageActivity activity = (ProfilePageActivity) getActivity();
        int userid = activity.getuserid();

        View view = inflater.inflate(R.layout.fragment_history, container, false);

        DatabaseHelper db = new DatabaseHelper(getActivity());

        arrayList = db.viewHistory();

        HistoryLV = (ListView) view.findViewById(R.id.listViewHistory);

        historyAdapter = new HistoryAdapter(activity, arrayList);
        HistoryLV.setAdapter(historyAdapter);
        historyAdapter.notifyDataSetChanged();


        HistoryLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(activity, HistoryTransactionDetail.class);

                b = arrayList.get(position).getDonation_image();
                ByteArrayOutputStream bStream = new ByteArrayOutputStream();
                b.compress(Bitmap.CompressFormat.PNG, 100, bStream);
                byte[] byteArray = bStream.toByteArray();


                intent.putExtra("id", arrayList.get(position).getDonation_id());
                intent.putExtra("user_id", arrayList.get(position).getUser_id());
                intent.putExtra("foundation_id", arrayList.get(position).getFoundation_id());
                intent.putExtra("quantity", arrayList.get(position).getQuantity());
                intent.putExtra("name", arrayList.get(position).getDonation_name().toString());
                intent.putExtra("description", arrayList.get(position).getDonation_description().toString());
                intent.putExtra("image", byteArray);
                intent.putExtra("foundation_name", arrayList.get(position).getFoundation_name().toString());
                intent.putExtra("foundation_description", arrayList.get(position).getFoundation_description().toString());
                intent.putExtra("foundation_address", arrayList.get(position).getFoundation_address().toString());

                startActivity(intent);
            }
        });


        return view;
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        arrayList = db.viewHistory();
//        ArrayAdapter.createFromResource(getActivity(),arrayList, android.R.layout.simple_list_item_1);
//
//        ArrayAdapter.cr
//    }
}