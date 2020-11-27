package com.beri.beriid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.beri.beriid.Model.User;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "beriiddatabase";
    private static final int DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlUsers = "CREATE TABLE users(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "email TEXT, " +
                "password TEXT, " +
                "nik TEXT, " +
                "address TEXT)";
//        String sqlProducts = "CREATE TABLE products(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                "name TEXT, " +
//                "description TEXT, " +
//                "quantity INTEGER)";
        String sqlFoundations = "CREATE TABLE foundations(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "description TEXT, " +
                "address TEXT)";
        String sqlDonations = "CREATE TABLE donations(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "user_id INTEGER,product_id INTEGER,foundation_id INTEGER,"+
                "name TEXT, " +
                "description TEXT, " +
                "quantity INTEGER," +
                "image BLOB," +
                "FOREIGN KEY(user_id) REFERENCES users(id), " +
                "FOREIGN KEY(foundation_id) REFERENCES foundations(id))";

        sqLiteDatabase.execSQL(sqlUsers);
//        sqLiteDatabase.execSQL(sqlProducts);
        sqLiteDatabase.execSQL(sqlFoundations);
        sqLiteDatabase.execSQL(sqlDonations);

    }

    public boolean addData(String name, String description, String address) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("description", description);
        contentValues.put("address", address);
        db.insert("foundations", null, contentValues);

        db.close();

        return true;
    }

    public boolean insertdatauser(String name,String email,String password,String nik,String address){
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put("name",name);
            contentValues.put("nik",nik);
            contentValues.put("password",password);
            contentValues.put("email",email);
            contentValues.put("address",address);
            long ins = db.insert("users",null,contentValues);
            if (ins==-1) return false;
            else return true;
    }


    public Boolean checkemail (String email){
        //BUAT DI REGIS CEK SI USER UDH PERNAH DAFTAR PAKE EMAIL YG DIA INPUT APA BELOM
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE email=? ",new String[]{email});
        if (cursor.getCount()>0) return false;
        else return true;
    }


    public Boolean checkuser (String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE email =? ",new String[]{email});

        if (cursor.getCount()>0) return true;
        else return false;
    }


    public int getuser (String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE email=? AND password =?",new String[]{email,password});

        int id = 0;
        while(cursor.moveToNext()){
            id = cursor.getInt(0);

        }

        return id;


    }






    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        String sqlUsers = "DROP TABLE IF EXISTS users";
        String sqlProducts = "DROP TABLE IF EXISTS products";
        String sqlFoundations = "DROP TABLE IF EXISTS foundations";
        String sqlDonations = "DROP TABLE IF EXISTS donations";

        sqLiteDatabase.execSQL(sqlUsers);
        sqLiteDatabase.execSQL(sqlProducts);
        sqLiteDatabase.execSQL(sqlFoundations);
        sqLiteDatabase.execSQL(sqlDonations);

        onCreate(sqLiteDatabase);
    }
}

