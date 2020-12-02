package com.beri.beriid;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.beri.beriid.Model.User;

import java.util.ArrayList;

import com.beri.beriid.DatabaseHelper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    DatabaseHelper db;
    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    String userid;
    ArrayList<User> userArrayList;

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

        ProfilePageActivity activity = (ProfilePageActivity) getActivity();
        String userid = activity.getuserid();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        DatabaseHelper db = new DatabaseHelper(getActivity());
//
//
//
//        db = new com.beri.beriid.DatabaseHelper(this);
//
        userArrayList = db.getAllUserData(userid);
//
//
        TextView profileusername = (TextView) view.findViewById(R.id.profile_username);
        TextView profilenik = (TextView) view.findViewById(R.id.profile_nik);
        TextView profileemail = (TextView) view.findViewById(R.id.profile_email);
        TextView profileaddress = (TextView) view.findViewById(R.id.profile_address);

        profileusername.setText(userArrayList.get(0).getUsername());
        profileemail.setText(userArrayList.get(0).getEmail());
        profileaddress.setText(userArrayList.get(0).getAddress());
        profilenik.setText(userArrayList.get(0).getNik());


        profileusername = profileusername.findViewById(R.id.profile_username);
        profilenik = profilenik.findViewById(R.id.profile_nik);
        profileemail = profileemail.findViewById(R.id.profile_email);
        profileaddress = profileaddress.findViewById(R.id.profile_address);
//
//        Intent intent = new Intent();
//
//        userid = intent.getStringExtra("userid");
//

//
//        profileusername.setText(userArrayList.get(0).getUsername());
//        profileemail.setText(userArrayList.get(0).getEmail());
//        profileaddress.setText(userArrayList.get(0).getAddress());
//        profilenik.setText(userArrayList.get(0).getNik());
        return view;
    }
}