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

import java.util.ArrayList;
import java.util.List;

public class ListOfItemsPageActivity extends AppCompatActivity implements UserObserver {
    TextView username;
    List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        User.getInstance().observe(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_items);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productList = new ArrayList<>();
        //Dummy data
        productList.add(
                new Product(
                  "Pablo's dog",
                  "A dog painted or drawn by picasso.",
                  "https://i.imgur.com/nBK4b1F.jpeg",
                        30
                )
        );
        productList.add(
                new Product(
                        "A picasso art piece",
                        "This is made by Picasso",
                        "https://i.imgur.com/DvpvklR.jpg",
                        30
                )
        );
        productList.add(
                new Product(
                        "A picasso art piece",
                        "This is made by Picasso",
                        "https://i.imgur.com/DvpvklR.jpg",
                        30
                )
        );
        productList.add(
                new Product(
                        "Pablo's dog",
                        "A dog painted or drawn by picasso. This is a longer description to test the description length.",
                        "https://i.imgur.com/nBK4b1F.jpeg",
                        30
                )
        );

        ListAdapter adapter = new ListAdapter(this, productList);
        recyclerView.setAdapter(adapter);

        //todo: if logged in set visibility
        username = findViewById(R.id.username);
        username.setOnClickListener( click -> {
            startActivity(new Intent(this, LoginActivity.class));
        });
    }

    @Override
    public void update() {
        username.setText(User.getInstance().getUserid());
    }
}