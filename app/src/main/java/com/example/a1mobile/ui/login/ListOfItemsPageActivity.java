package com.example.a1mobile.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.example.a1mobile.R;

import org.json.JSONException;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ListOfItemsPageActivity extends AppCompatActivity implements UserObserver {
    public static final String BASEURL = "http://10.0.2.2:8080/api/";
    TextView username;
    TextView logout;
    List<Product> productList;
    RequestQueue requestQueue;

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

        logout = findViewById(R.id.logout);
        logout.setVisibility(View.GONE);
        logout.setOnClickListener(click -> {
            User user = User.getInstance();
            user.clearAll();
            logout();
        });

        username = findViewById(R.id.username);
        username.setOnClickListener( click -> {
            startActivity(new Intent(this, LoginActivity.class));
        });
    }

    @Override
    public void update() {
        User user = User.getInstance();
        if(user.getToken() != null){
            logout.setVisibility(View.VISIBLE);
            username.setText(User.getInstance().getUserid());
        }
    }

    /*public void authUser(){
        String URL = BASEURL + "auth/currentuser";
        User user = User.getInstance();
        requestQueue.add(new StringRequest(Request.Method.GET, URL, response -> {
                if(response.contains(user.getUserid())){
                   Log.i("Response", response);
                }
            }, error -> {
            Log.i("ERROR", String.valueOf(error));
        }));
    }*/

    private void logout(){
        username.setText("Sign in");
        logout.setVisibility(View.GONE);
    }
}