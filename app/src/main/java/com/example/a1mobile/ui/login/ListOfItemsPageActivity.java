package com.example.a1mobile.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a1mobile.R;

public class ListOfItemsPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_items);

        TextView username = findViewById(R.id.username);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); // set the toolbar to the activity's toolbar.

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}