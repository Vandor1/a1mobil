package com.example.a1mobile.ui.login;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.a1mobile.R;

public class NewItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_item);

        Button cancel = findViewById(R.id.cancelNewItem);
        cancel.setOnClickListener(click -> finish());
    }
}
