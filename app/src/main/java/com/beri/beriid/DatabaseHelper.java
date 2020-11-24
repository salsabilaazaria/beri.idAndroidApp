package com.beri.beriid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.beri.beriid.Model.Foundation;

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
        String sqlProducts = "CREATE TABLE products(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "description TEXT, " +
                "quantity INTEGER)";
        String sqlFoundations = "CREATE TABLE foundations(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "description TEXT, " +
                "address TEXT)";
        String sqlDonations = "CREATE TABLE donations(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "user_id INTEGER,product_id INTEGER,foundation_id INTEGER,"+
                "FOREIGN KEY(user_id) REFERENCES users(id), " +
                "FOREIGN KEY(product_id) REFERENCES products(id), " +
                "FOREIGN KEY(foundation_id) REFERENCES foundations(id))";

        sqLiteDatabase.execSQL(sqlUsers);
        sqLiteDatabase.execSQL(sqlProducts);
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

    public ArrayList<Foundation> getAllYayasanData(){
        ArrayList<Foundation> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT *FROM foundations", null);

        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String desc = cursor.getString(2);
            String address = cursor.getString(3);

            Foundation foundation = new Foundation(id,name,desc,address);


            arrayList.add(foundation);


        }

        return arrayList;


    }


}

