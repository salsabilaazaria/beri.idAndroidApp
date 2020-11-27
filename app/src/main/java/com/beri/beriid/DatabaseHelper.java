package com.beri.beriid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.beri.beriid.Model.Donation;
import com.beri.beriid.Model.Foundation;
import com.beri.beriid.Model.History;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "beriiddatabase";
    private static final int DB_VERSION = 1;

    private ByteArrayOutputStream objectByteArrayOutputStream;
    private byte[] imageInBytes;

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

    public void addDonationData(Donation Donationob) {

        SQLiteDatabase db = this.getWritableDatabase();
        Bitmap imageToStoreBitmap = Donationob.getImage();
        objectByteArrayOutputStream = new ByteArrayOutputStream();
        imageToStoreBitmap.compress(Bitmap.CompressFormat.JPEG, 100, objectByteArrayOutputStream);

        imageInBytes = objectByteArrayOutputStream.toByteArray();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_id", Donationob.getUser_id());
        contentValues.put("foundation_id", Donationob.getFoundation_id());
        contentValues.put("quantity", Donationob.getQuantity());
        contentValues.put("name", Donationob.getName());
        contentValues.put("description", Donationob.getDescription());
        contentValues.put("image", imageInBytes);

        db.insert("donations", null, contentValues);
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
//        String sqlProducts = "DROP TABLE IF EXISTS products";
        String sqlFoundations = "DROP TABLE IF EXISTS foundations";
        String sqlDonations = "DROP TABLE IF EXISTS donations";

        sqLiteDatabase.execSQL(sqlUsers);
//        sqLiteDatabase.execSQL(sqlProducts);
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


    public ArrayList<History> viewHistory() {
        ArrayList<History> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM donations, foundations WHERE donations.foundation_id = foundations.id", null);

        while(cursor.moveToNext()) {
            int id = cursor.getInt(0);
            int user_id = cursor.getInt(1);
            int foundation_id = cursor.getInt(2);
            String name = cursor.getString(3);
            String description = cursor.getString(4);
            int quantity = cursor.getInt(5);
//            String image = cursor.getString(6);

            byte[] image2 = cursor.getBlob(6);
            Bitmap image = BitmapFactory.decodeByteArray(image2,0, image2.length);

            String foundation_name = cursor.getString(8);
            String foundation_description = cursor.getString(9);
            String foundation_address = cursor.getString(10);

            History history = new History(user_id, id, foundation_id, quantity, foundation_name, foundation_description, foundation_address, name, description, image);

            arrayList.add(history);
        }

        return arrayList;
    }

    Cursor readYayasanData(){
        String query = "SELECT * FROM Foundations";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db!= null){
            cursor = db.rawQuery(query, null);

        }
        return cursor;
    }


}

