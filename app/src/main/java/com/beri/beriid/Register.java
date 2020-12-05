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
                String passVal = "^" + "(?=.*[a-zA-z])" + "(?=.*[0-9])" + "(?=\\S+$)" + ".{8,}" + "$";
                String emailVal = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String nikVal = "^(?=.*[0-9]).{12,}$";
                if (sName.equals("")||sEmail.equals("")||sPassword.equals("")||sVerify.equals("")||sAddress.equals("")){
                    Toast.makeText(getApplicationContext(),"Please insert all data",Toast.LENGTH_SHORT).show();
                } else if (!sEmail.matches(emailVal)){
                    Toast.makeText(getApplicationContext(),"Insert a valid Email",Toast.LENGTH_SHORT).show();
                } else if (!sPassword.equals(sVerify)) {
                    Toast.makeText(getApplicationContext(), "Passwords does not match", Toast.LENGTH_SHORT).show();
                } else if (!sPassword.matches(passVal)) {
                    Toast.makeText(getApplicationContext(), "Password cannot has whitespaces, only has letters and numbers and minimum of 8 characters", Toast.LENGTH_SHORT).show();
                } else if(!sNIK.matches(nikVal)) {
                    Toast.makeText(getApplicationContext(), "NIK can only contain numbers and minimum of 12 character", Toast.LENGTH_SHORT).show();
                } else{
                    Boolean checkemail = db.checkemail(sEmail);
                    if (checkemail==true){
                        if(!sPassword.matches(passVal)){
                        }else{
                            Boolean insert = db.insertdatauser(sName,sEmail,sPassword,sNIK,sAddress);
                            if (insert==true){
                                Toast.makeText(getApplicationContext(),"Registered Successful",Toast.LENGTH_SHORT).show();

                                int id = db.getuser(sEmail,sPassword);
                                Intent i = new Intent(getApplicationContext(),HomePageActivity.class);
                                i.putExtra("user_id", id);

                                startActivity(i);
                            }
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Email Already Exist",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    @Override
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}