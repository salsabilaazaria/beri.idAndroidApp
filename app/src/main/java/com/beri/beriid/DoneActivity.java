package com.beri.beriid;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class DoneActivity extends AppCompatActivity {

    ImageView done;
    Button button;
    int user_id;

    AnimatedVectorDrawableCompat avd;
    AnimatedVectorDrawable avd2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);

        done = findViewById(R.id.done);
        button = findViewById(R.id.backToHome);

        Intent intent = getIntent();

        user_id = intent.getIntExtra("user_id", 0);

        Drawable drawable = done.getDrawable();
        if(drawable instanceof AnimatedVectorDrawableCompat){
            avd = (AnimatedVectorDrawableCompat) drawable;
            avd.start();
        } else if (drawable instanceof AnimatedVectorDrawable){
            avd2 = (AnimatedVectorDrawable) drawable;
            avd2.start();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), HomePageActivity.class);
                i.putExtra("user_id", user_id);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        Intent i = new Intent(getApplicationContext(), HomePageActivity.class);
        i.putExtra("user_id", user_id);
        startActivity(i);
    }
}