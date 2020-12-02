package com.beri.beriid;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.beri.beriid.Adapters.ProfilePageAdapter;
import com.beri.beriid.Model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;


import java.util.ArrayList;

public class ProfilePageActivity extends AppCompatActivity {
    int userid;
    DrawerLayout drawerLayout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);

        userid = getIntent().getIntExtra("userid",0);

        //bottom navigation
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_home:
                        Intent intent = new Intent(ProfilePageActivity.this, HomePageActivity.class);
                        intent.putExtra("user_id",userid);
                        startActivity(intent);
                        return true;

                    case R.id.nav_profie:

                }
                return false;
            }

        });




        //NAV DRAWER

        drawerLayout = findViewById(R.id.drawer_layout);




        TabLayout tabLayout = findViewById(R.id.tabBar);
        TabItem tabProfile = findViewById(R.id.ProfilePageTab);
        TabItem tabHistory = findViewById(R.id.HistoryTransactionTab);
        final ViewPager viewPager = findViewById(R.id.viewPager);

        ProfilePageAdapter profilePageAdapter = new ProfilePageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(profilePageAdapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



    }
    public int getuserid(){
        return userid;
    }

    //NAVDRAWER

    public void ClickMenu(View view){
        openDrawer(drawerLayout);

    }

    private static void openDrawer(DrawerLayout drawerLayout) {
        //open drawer
        drawerLayout.openDrawer(GravityCompat.START);

    }

    public void ClickLogo(View view){

        closeDrawer(drawerLayout);
    }

    private static void closeDrawer(DrawerLayout drawerLayout) {
        //close drawer
        //check condition
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            //when drawer is open, close
            drawerLayout.closeDrawer(GravityCompat.START);

        }

    }

    public void ClickLogout(View view){
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }


    protected void onPause(){
        super.onPause();
        closeDrawer(drawerLayout);
    }


    // CODINGAN LAMA

//    TextView profileusername;
//    TextView profilenik;
//    TextView profileemail;
//    TextView profileaddress;
//
//    com.beri.beriid.DatabaseHelper db;
//    String userid;
//    ArrayList<User> userArrayList;
//
//    private TextView transactionhistorybutton;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.profile_page);
//
////        profileusername = findViewById(R.id.profile_username);
////        profilenik = findViewById(R.id.profile_nik);
////        profileemail = findViewById(R.id.profile_email);
////        profileaddress = findViewById(R.id.profile_address);
//
////        Intent intent  = getIntent();
////
////        userid = intent.getStringExtra("userid");
//
//
//        db = new com.beri.beriid.DatabaseHelper(this);
//
//        userArrayList = db.getAllUserData(userid);
//
//        profileusername.setText(userArrayList.get(0).getUsername());
//        profileemail.setText(userArrayList.get(0).getEmail());
//        profileaddress.setText(userArrayList.get(0).getAddress());
//        profilenik.setText(userArrayList.get(0).getNik());
//
//        //Transaction History Button
////        transactionhistorybutton = (TextView) findViewById(R.id.profile_transactionhistorybtn);
//        transactionhistorybutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                opentransactionhistorypage();
//            }
//
//        });
//    }
//
//    public void opentransactionhistorypage(){
//        Intent i = new Intent(this,HomePageActivity.class);
//        startActivity(i);
//    }


}
