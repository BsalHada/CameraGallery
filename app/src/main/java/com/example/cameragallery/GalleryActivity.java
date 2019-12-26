package com.example.cameragallery;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class GalleryActivity extends AppCompatActivity {
Button btn_gallery;
ImageView imageview_gallery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        imageview_gallery=findViewById(R.id.imageview_gallery);
        btn_gallery=findViewById(R.id.btn_gallery);
        btn_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseImage();
            }
        });

    }


    private void BrowseImage(){

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(data==null){
                Toast.makeText(this,"Please select an image",Toast.LENGTH_SHORT).show();

            }

        }
        Uri uri = data.getData();
        imageview_gallery.setImageURI(uri);
        String imgPath = getRealPathFromUri(uri);
        Toast.makeText(this,"emulator/images/png"+imgPath,Toast.LENGTH_SHORT).show();

    }

    private String getRealPathFromUri (Uri uri){
        String[] projection ={MediaStore.Audio.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(),uri, projection,null,null,null);
        Cursor cursor = loader. loadInBackground();
        int colIndex= cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
        cursor.moveToFirst();
        String result= cursor.getString(colIndex);
        cursor.close();
        return result;
    }
}
