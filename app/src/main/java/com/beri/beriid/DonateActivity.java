package com.beri.beriid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.beri.beriid.Model.Donation;
import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.io.InputStream;

public class DonateActivity extends AppCompatActivity {

    Button button;
    TextInputLayout name, desc, quantity;
    private static final int REQUEST_CODE_STORAGE_PERMISSION = 1;
    private static final int REQUEST_CODE_SELECT_IMAGE = 2;
    private ImageView imageSelected;
    Bitmap imageToStore;
    int user_id, f_id;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);

        Intent intent = getIntent();

        user_id = intent.getIntExtra("user_id", 0);
        f_id = intent.getIntExtra("f_id", 0);

        imageSelected = findViewById(R.id.imageSelected);
        findViewById(R.id.selectImageButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(DonateActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_STORAGE_PERMISSION);
                } else{
                    selectImage();
                }
            }
        });

        name = findViewById(R.id.productName);
        desc = findViewById(R.id.productDescription);
        quantity = findViewById(R.id.productQuantity);

        db = new DatabaseHelper(this);
    }

    private void selectImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_CODE_STORAGE_PERMISSION && grantResults.length > 0){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                selectImage();
            }else{
                Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SELECT_IMAGE && resultCode == RESULT_OK){
            if (data != null){
                Uri selectedImageUri = data.getData();
                if (selectedImageUri != null){
                    try {
                        InputStream inputStream = getContentResolver().openInputStream(selectedImageUri);
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        imageSelected.setImageBitmap(bitmap);

                        imageToStore = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                        File selectedImageFile = new File(getPathFromUri(selectedImageUri));
                    }catch (Exception exception){
                        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    private String getPathFromUri(Uri contentUri){
        String filePath;
        Cursor cursor = getContentResolver().query(contentUri, null, null, null, null);
        if (cursor == null){
            filePath = contentUri.getPath();
        }else {
            cursor.moveToFirst();
            int index = cursor.getColumnIndex("_data");
            filePath = cursor.getString(index);
            cursor.close();
        }
        return filePath;
    }

    public void storeImage(View view){
        if(!name.getEditText().getText().toString().isEmpty() && !desc.getEditText().getText().toString().isEmpty() && !quantity.getEditText().getText().toString().isEmpty() && imageSelected.getDrawable()!=null){
            db.addDonationData(new Donation(user_id, f_id, Integer.parseInt(quantity.getEditText().getText().toString()), name.getEditText().getText().toString(), desc.getEditText().getText().toString(), imageToStore));
            Toast.makeText(this, "Data added!", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, DoneActivity.class);
            i.putExtra("user_id", user_id);
            startActivity(i);
        }else{
            Toast.makeText(this, "Please insert data", Toast.LENGTH_SHORT).show();
        }
    }
}