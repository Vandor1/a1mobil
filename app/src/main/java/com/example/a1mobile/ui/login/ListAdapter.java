package com.example.a1mobile.ui.login;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a1mobile.R;

/**
 * https://developer.android.com/guide/topics/ui/layout/recyclerview#java
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{
    OnClickListener listener = position -> {};

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    interface OnClickListener {
        void onClick(int position);
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder viewHolder, int position) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            view.setOnClickListener(v -> listener.onClick(getAdapterPosition()));
            textView = (TextView) view.findViewById(R.id.listItemTitle);
        }

        public TextView getTextView() {
            return textView;
        }
    }
}
