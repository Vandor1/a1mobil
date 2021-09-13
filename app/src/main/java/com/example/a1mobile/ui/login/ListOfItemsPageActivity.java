package com.example.a1mobile.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a1mobile.R;

import org.json.JSONException;

public class ListOfItemsPageActivity extends AppCompatActivity implements UserObserver {
    TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        User.getInstance().observe(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_items);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String title = findViewById(R.id.listItemTitle).toString();
        String desc = findViewById(R.id.listItemDesc).toString();
        int img = Integer.parseInt(findViewById(R.id.listItemImage).toString());

        ListAdapter adapter = new ListAdapter(this, title, desc, img);
        username = findViewById(R.id.username);

        username.setOnClickListener( click -> {
            startActivity(new Intent(this, LoginActivity.class));
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
    }

    @Override
    public void update() {
        username.setText(User.getInstance().getUserid());
    }
}