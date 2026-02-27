package com.example.bookrecommender.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.bookrecommender.ListItemsActivity;
import com.example.bookrecommender.R;

import java.util.ArrayList;

public class ReadListFragment extends Fragment { // Fragments suck tbh

    ArrayList<String> lists = new ArrayList<>();
    ArrayAdapter<String> adapter;

    public ReadListFragment() {
        // Required empty constructor to work
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_read_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListView listView = view.findViewById(R.id.listViewLists);
        Button btnAdd = view.findViewById(R.id.addList);

        adapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_list_item_1,
                lists);

        listView.setAdapter(adapter);

        btnAdd.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            builder.setTitle("Name your new Read List");

            final EditText input = new EditText(requireContext());
            input.setHint("e.x. My favourite books");
            builder.setView(input);

            builder.setPositiveButton("Create", (dialog, which) -> {
                String name = input.getText().toString().trim();
                if (!name.isEmpty()) {
                    lists.add(name);
                    adapter.notifyDataSetChanged();
                }
            });

            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
            builder.show();
        });

        listView.setOnItemClickListener((parent, itemView, position, id) -> {
            Intent intent = new Intent(requireContext(), ListItemsActivity.class);
            intent.putExtra("listName", lists.get(position));
            startActivity(intent);
        });

        listView.setOnItemLongClickListener((parent, itemView, position, id) -> {
            lists.remove(position);
            adapter.notifyDataSetChanged();
            return true;
        });
    }
}
