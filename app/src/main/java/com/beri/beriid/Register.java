package com.beri.beriid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    DatabaseHelper db;
    EditText e1,e2,e3,e4,e5;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = new DatabaseHelper(this);
        e1=(EditText)findViewById(R.id.InputEmail);
        e2=(EditText)findViewById(R.id.InputPassword);
        e3=(EditText)findViewById(R.id.VerifPassword);
        e4=(EditText)findViewById(R.id.InputName);
        e5=(EditText)findViewById(R.id.InputAddress);
        b1=(Button)findViewById(R.id.ButtonConfirm);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                String s3 = e3.getText().toString();
                String s4 = e4.getText().toString();
                String s5 = e5.getText().toString();
                if (s1.equals("")||s2.equals("")||s3.equals("")||s4.equals("")||s5.equals("")){
                    Toast.makeText(getApplicationContext(),"Fields are empty",Toast.LENGTH_SHORT).show();
                }
                else {
                    if (s2.equals(s3)){
                        Boolean checkemail = db.checkemail(s1);
                        if (checkemail==true){
                            Boolean insert = db.insertdatauser(s1,s2,s3,s4,s5);
                            if (insert==true){
                                Toast.makeText(getApplicationContext(),"Registered Successfullf",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Email Already Exist",Toast.LENGTH_SHORT).show();
                        }
                    }
                    Toast.makeText(getApplicationContext(), "Password do not match",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}