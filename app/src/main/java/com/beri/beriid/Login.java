package com.beri.beriid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
DatabaseHelper db;
int id;

private EditText email, password;
private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DatabaseHelper(this);

        email = (EditText)findViewById(R.id.LoginEmail);
        password = (EditText)findViewById(R.id.LoginPassword);
        login = (Button)findViewById(R.id.buttonLogin);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sEmail = email.getText().toString();//s2
                String sPassword = password.getText().toString();//s3


                if (sEmail.equals("")||sPassword.equals("")){
                    Toast.makeText(getApplicationContext(),"Fields are empty",Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean checklogin = db.checkuser(sEmail, sPassword);

                    if (checklogin==true){
                        Toast.makeText(getApplicationContext(),"Login Succesfully",Toast.LENGTH_SHORT).show();
                        id = db.getuser(sEmail,sPassword);

                        Intent i = new Intent(getApplicationContext(),HomePageActivity.class);
                        i.putExtra("user_id", id);

                        startActivity(i);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"You haven't register",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });










    }




    public void gantiregis(View view) {
        Intent intent = new Intent(Login.this, Register.class);
        startActivity(intent);
    }
}