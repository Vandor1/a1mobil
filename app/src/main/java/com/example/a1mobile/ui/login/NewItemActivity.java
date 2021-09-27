package com.example.a1mobile.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.a1mobile.R;

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

        setupToolbar();
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
            logout.setVisibility(View.VISIBLE);
            username.setText(User.getInstance().getUserid());
        }
    }
}
