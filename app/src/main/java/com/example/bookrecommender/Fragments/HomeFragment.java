package com.example.bookrecommender.Fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.bookrecommender.R;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private Button btnSearch, btnHistory;

    public HomeFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // turning the XML layout into a View object
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.rv_current_books);
        btnSearch = view.findViewById(R.id.btn_search);

        setupRecyclerView();
        setupButtons();

        return view;
    }

    private void setupRecyclerView() {
        // Sets the scrolling to horizontal as per your sketch
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // Uses the 6 placeholder boxes since Book.java is empty
        BookAdapter adapter = new BookAdapter(6);
        recyclerView.setAdapter(adapter);
    }

    private void setupButtons() {
        // Search Button Logic
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Placeholder action for search
                Toast.makeText(getContext(), "Searching through current reads...", Toast.LENGTH_SHORT).show();

                //update this when i learn how to link searches to available database
            }
        });

    }
}