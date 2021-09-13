package com.example.a1mobile.ui.login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a1mobile.R;

import java.util.List;

/**
 * https://developer.android.com/guide/topics/ui/layout/recyclerview#java
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{
    private String murl = "http://192.168.10.158:8080/api/";
    private String imgURL = murl+"fant/photo/";

    private List<Product> listItems;
    Context context;

    public ListAdapter(Context context, List<Product> listItems){
        this.context = context;
        this.listItems = listItems;
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder viewHolder, int position) {
        Product product = listItems.get(position);
        viewHolder.desc.setText(product.getDescription());
        viewHolder.title.setText(product.getTitle());
        setImageBitmap(viewHolder.img);
/*
        viewHolder.desc.setText(product.getPrice());
*/
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, desc;
        ImageView img;

        public ViewHolder(@NonNull View view) {
            super(view);
            title = view.findViewById(R.id.listItemTitle);
            desc = view.findViewById(R.id.listItemDesc);
            img = view.findViewById(R.id.listItemImage);
            }
    }

    private void setImageBitmap(ImageView imageView){
        Picasso.get()
                .load(imgURL)
                .placeholder(R.drawable.abc_vector_test)
                .centerCrop()
                .fit()
                .noFade()
                .into(imageView);
    }
}
