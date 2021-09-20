package com.example.a1mobile.ui.login;

import android.content.Context;
import android.net.Uri;
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
    private String murl = "http://10.0.2.2:8080/api/";
    private String imgURL = murl + "fant/photo/";
    private OnListItemListener onListItemListener;
    private List<Product> listItems;
    Context context;

    public ListAdapter(Context context, List<Product> listItems, OnListItemListener onListItemListener){
        this.context = context;
        this.listItems = listItems;
        this.onListItemListener = onListItemListener;
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view, onListItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {
        Product product = listItems.get(position);
        holder.desc.setText(product.getDescription());
        holder.title.setText(product.getTitle());
        Picasso.get().load(Uri.parse(product.getImageURL())).into(holder.img);
        /*setImageBitmap(holder.img)*/;
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title, desc;
        ImageView img;
        OnListItemListener onListItemListener;

        public ViewHolder(@NonNull View view, OnListItemListener onListItemListener) {
            super(view);
            title = view.findViewById(R.id.listItemTitle);
            desc = view.findViewById(R.id.listItemDesc);
            img = view.findViewById(R.id.listItemImage);
            this.onListItemListener = onListItemListener;

            view.setOnClickListener(this);
            }

        @Override
        public void onClick(View view) {
            onListItemListener.onItemClick(getAdapterPosition());
        }

    }

    public interface OnListItemListener{void onItemClick(int position);}

    private void setImagePicasso(ImageView imageView){
        Picasso.get()
                .load(imgURL)
                .placeholder(R.drawable.ic_launcher_background)
                .centerCrop()
                .fit()
                .noFade()
                .into(imageView);
    }
}
