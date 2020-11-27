package com.beri.beriid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    DatabaseHelper db;
    EditText eName,eEmail,ePassword,eAddress,eVerify, eNIK;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = new DatabaseHelper(this);

        eName=(EditText)findViewById(R.id.InputName);
        eEmail=(EditText)findViewById(R.id.LoginEmail);
        ePassword=(EditText)findViewById(R.id.InputPassword);
        eVerify=(EditText)findViewById(R.id.VerifPassword);
        eAddress=(EditText)findViewById(R.id.InputAddress);
        eNIK = (EditText)findViewById(R.id.InputNIK);
        b1=(Button)findViewById(R.id.ButtonConfirm);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sName = eName.getText().toString();//s1
                String sEmail = eEmail.getText().toString();//s2
                String sPassword = ePassword.getText().toString();//s3
                String sVerify = eVerify.getText().toString();//s4
                String sAddress = eAddress.getText().toString();//s5
                String sNIK = eNIK.getText().toString();//s6
//                public boolean insertdatauser(String name,String email,String password,String nik,String address){
                if (sName.equals("")||sEmail.equals("")||sPassword.equals("")||sVerify.equals("")||sAddress.equals("")){
                    Toast.makeText(getApplicationContext(),"Fields are empty",Toast.LENGTH_SHORT).show();
                }
                if (!sPassword.equals(sVerify)){
                    Toast.makeText(getApplicationContext(),"Password not same",Toast.LENGTH_SHORT).show();
                }
                else {
                    if (sVerify.equals(sPassword)){
                        Boolean checkemail = db.checkemail(sEmail);
                        if (checkemail==true){
                            Boolean insert = db.insertdatauser(sName,sEmail,sPassword,sNIK,sAddress);
                            if (insert==true){
                                Toast.makeText(getApplicationContext(),"Registered Successfullf",Toast.LENGTH_SHORT).show();

                                int id = db.getuser(sEmail,sPassword);
                                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                                i.putExtra("userid", Integer.toString(id));


                                startActivity(i);
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