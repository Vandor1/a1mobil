package com.example.a1mobile.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.example.a1mobile.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListOfItemsPageActivity extends AppCompatActivity implements UserObserver, ListAdapter.OnListItemListener {
    public static final String BASEURL = "http://10.0.2.2:8080/api/";
    TextView username;
    TextView logout;
    List<Product> productList;
    List<String> imageUrls;
    RequestQueue requestQueue;
    SearchView search;
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        User.getInstance().observe(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_items);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productList = new ArrayList<>();
        imageUrls = new ArrayList<>();

        addDummyData();

        adapter = new ListAdapter(this, productList, this);
        recyclerView.setAdapter(adapter);

        setupToolbar();

        FloatingActionButton floatingActionButton = findViewById(R.id.newItemButton);
        floatingActionButton.setOnClickListener( click ->{
            if (User.getInstance().getToken() != null){
                startActivity(new Intent(this, NewItemActivity.class));
            } else {
                Toast toast = Toast.makeText(this, "You need to be logged in to add items!", Toast.LENGTH_SHORT);
                toast.show();
                System.out.println("Cannot login!");
            }
        });

        search = findViewById(R.id.search_bar);
        search.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {
                    /**
                     * TODO: Dummy data is filtered once and there is no way to return to the original list as of now.
                     */
                    @Override
                    public boolean onQueryTextSubmit(String s) {
                        ArrayList<Product> foundItems = new ArrayList<>();
                        productList.stream().filter(searchQuery -> searchQuery.getTitle().contains(s))
                                .forEach(foundItems::add);
                        adapter.setFilteredList(foundItems);;
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String s) {
                        return false;
                    }
                }
        );
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


    private void addDummyData() {
        imageUrls.add("https://i.imgur.com/nBK4b1F.jpeg");
        imageUrls.add("https://i.imgur.com/nBK4b1F.jpeg");
        productList.add(new Product("Pablo's dog", "A dog painted or drawn by picasso.", imageUrls, 30));
        productList.add(new Product("A picasso art piece", "This is made by Picasso", imageUrls, 30));
        productList.add(new Product("A picasso art piece", "This is made by Picasso", imageUrls, 30));
        productList.add(new Product("Pablo's dog", "A dog painted or drawn by picasso. This is a longer description to test the description length.", imageUrls, 30));
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
        username.setText(R.string.action_sign_in_short);
        logout.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(int position) {
        Log.i("Clicked item on list.", "onItemClick, position: " + position);
        Intent intent = new Intent(this, ItemDetailsActivity.class);

        intent.putExtra("selected_product", productList.get(position));
        startActivity(intent);
    }
}