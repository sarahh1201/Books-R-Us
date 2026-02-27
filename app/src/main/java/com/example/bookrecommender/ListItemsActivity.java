package com.example.bookrecommender;

import android.app.Fragment;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


// This is nested in the Read List, when you select a specific read list in order to
// view and modify the individual books

public class ListItemsActivity extends AppCompatActivity {
    ArrayList<String> items = new ArrayList<>();
    ArrayAdapter<String> adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_readlist_items);
        super.onCreate(savedInstanceState);
        String listName = getIntent().getStringExtra("listName");
        setTitle(listName);
        ListView listView = findViewById(R.id.listViewItems);
        Button btnAdd = findViewById(R.id.addItem); adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);
        btnAdd.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(ListItemsActivity.this);
            builder.setTitle("Add a book:");

            final EditText input = new EditText(ListItemsActivity.this);
            input.setHint("e.x. Harry Potter, 1984, etc.");
            builder.setView(input);

            builder.setPositiveButton("Add", (dialog, which) -> {
                String name = input.getText().toString().trim();
                if (!name.isEmpty()) {
                    items.add(name);
                    adapter.notifyDataSetChanged();
                }
            });

            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

            builder.show();
        });
        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            items.remove(position);
            adapter.notifyDataSetChanged();
            return true;
        });
    }
}
