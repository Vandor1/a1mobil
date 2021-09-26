package com.example.a1mobile.ui.login;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.a1mobile.R;
import com.squareup.picasso.Picasso;

public class ItemDetailsActivity extends AppCompatActivity {

    private static final String TAG = "ItemDetailsActivity";
    private static final String WRONG = "Something went wrong!";
    TextView title;
    TextView description;
    TextView price;
    ViewPager2 viewPager2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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
            price.setText(String.valueOf(product.getPrice()));

            Picasso.get().load(Uri.parse(product.getImageURL())).into(viewPager2);


        } else {
            title.setText(WRONG);
            description.setText(WRONG);
            price.setText(WRONG);
        }
    }
}


