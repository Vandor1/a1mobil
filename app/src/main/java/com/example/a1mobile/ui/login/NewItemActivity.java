package com.example.a1mobile.ui.login;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.example.a1mobile.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewItemActivity extends AppCompatActivity implements UserObserver{

    EditText title;
    EditText price;
    EditText desc;
    //List<String> imageUrls;
    String imageUrl;
    Button addImageButton;
    Button addItemButton;

    TextView username;
    TextView logout;

    File currentPhoto;
    List<File> photoFiles = new ArrayList<>();
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final String FILEPROVIDER = "com.example.a1mobile.fileprovider";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        User.getInstance().observe(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_item);

        Button cancel = findViewById(R.id.cancelNewItem);
        cancel.setOnClickListener(click -> finish());

        addImageButton = findViewById(R.id.addImageButton);
        addItemButton = findViewById(R.id.generateNewItem);
        title = findViewById(R.id.newItemTitleInput);
        price = findViewById(R.id.priceInput);
        desc = findViewById(R.id.newItemDescInput);

        addImageButton.setOnClickListener(click -> {
            onCameraClicked();
        });
        setupToolbar();
    }

    public void onCameraClicked(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        System.out.println("Clicked add image.");
        if (intent.resolveActivity(getPackageManager()) != null){
            currentPhoto = createImageFile();
            System.out.println("1");
            if(currentPhoto != null){
                System.out.println("2");
                Uri photoURI = FileProvider.getUriForFile(this, FILEPROVIDER, currentPhoto);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private File createImageFile(){
        File result = null;

        System.out.println("Creating image file..");
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        try{
            result = File.createTempFile(imageFileName, ".jpg", storageDir);
            System.out.println("File is " + result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            try {
                if(requestCode == REQUEST_IMAGE_CAPTURE){
                    photoFiles.add(currentPhoto);
                    currentPhoto = null;
                }
            } catch ( Exception e ){
                e.printStackTrace();
            }
        }
    }

    private void setupToolbar() {
        logout = findViewById(R.id.logout);
        logout.setVisibility(View.GONE);
        logout.setOnClickListener(click -> {
            User user = User.getInstance();
            user.clearAll();
            logout();
        });
        username = findViewById(R.id.username);
        username.setOnClickListener( click -> { startActivity(new Intent(this, LoginActivity.class)); });
    }

    private void logout(){
        username.setText(R.string.action_sign_in_short);
        logout.setVisibility(View.GONE);
    }

    @Override
    public void update() {
        User user = User.getInstance();
        if(user.getToken() != null){
            System.out.println(user.getToken());
            logout.setVisibility(View.VISIBLE);
            username.setText(User.getInstance().getUserid());
        }
    }
}
