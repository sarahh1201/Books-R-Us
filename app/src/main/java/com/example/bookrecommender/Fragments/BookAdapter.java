package com.example.bookrecommender.Fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bookrecommender.R;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private int numberOfItems;

    // We pass in 6 so it knows how many boxes to draw for the recycler view
    public BookAdapter(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        // This is left empty for now because Book.java has no data yet
    }

    @Override
    public int getItemCount() {
        return numberOfItems; // Returns the number (6) we passed in earlier
    }

    // This class finds the actual views inside item_book.xml
    public static class BookViewHolder extends RecyclerView.ViewHolder {
        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}