package com.example.a1mobile.ui.login;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.a1mobile.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ItemDetailsActivity extends AppCompatActivity implements UserObserver {

    private static final String TAG = "ItemDetailsActivity";
    private static final String WRONG = "Something went wrong!";
    TextView title;
    TextView description;
    TextView price;
    ViewPager2 viewPager2;
    ImageAdapter imageAdapter;

    TextView username;
    TextView logout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        User.getInstance().observe(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_item_details);

        viewPager2 = findViewById(R.id.viewPagerDetails);
        title = findViewById(R.id.itemDetailsTitle);
        price = findViewById(R.id.itemDetailsPrice);
        description = findViewById(R.id.itemDetailsDesc);

        if(getIntent().hasExtra("selected_product")){
            Product product = getIntent().getParcelableExtra("selected_product");
            Log.d(TAG, "onCreate: " + product.toString());
            title.setText(product.getTitle());
            description.setText(product.getDescription());
            price.setText((product.getPrice() + " kr"));

            // Todo: hehe..
            List<Product> productList = new ArrayList<>();
            productList.add(product);
            productList.add(product);
            imageAdapter = new ImageAdapter(productList);
            viewPager2.setAdapter(imageAdapter);
        } else {
            title.setText(WRONG);
            description.setText(WRONG);
            price.setText(WRONG);
        }
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


