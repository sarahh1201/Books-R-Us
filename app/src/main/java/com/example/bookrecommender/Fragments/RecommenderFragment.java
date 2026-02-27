package com.example.bookrecommender.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookrecommender.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecommenderFragment extends Fragment
        implements AdapterView.OnItemSelectedListener {

    private Button buttonRecommend;
    private ArrayAdapter<String> recommendationsAdapter;
    private ListView lvRecommendations;
    private TextView genreRecommend;

    // The Options in the Spinner (dropdown)
    private String[] genresList = {
            "Fantasy", "Romance", "Sci-Fi",
            "Drama", "Comedy", "Murder Mystery", "Mystery"
    };
    // Organize the Books into a HashMap
    private Map<String, List<Book>> booksByGenre = new HashMap<>();

    public RecommenderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommender, container, false);

        // Configurations
        Spinner genres = view.findViewById(R.id.spinner_genre);
        buttonRecommend = view.findViewById(R.id.button_recommend);
        lvRecommendations = view.findViewById(R.id.lvRecommendations);
        genreRecommend = view.findViewById(R.id.txtRecommend);

        // Adapter for dropdown
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                genresList
        );
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        genres.setAdapter(adapter);
        genres.setOnItemSelectedListener(this);

        // Input books into Map
        booksByGenre.put("Fantasy", Arrays.asList(
                new Book("Harry Potter"),
                new Book("Lord of the Rings")
        ));
        booksByGenre.put("Romance", Arrays.asList(
                new Book("Pride and Prejudice"),
                new Book("The Notebook")
        ));

        booksByGenre.put("Sci-Fi", Arrays.asList(
                new Book("Dune"),
                new Book("Ender's Game")
        ));

        // Adapter for the ListView
        recommendationsAdapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_list_item_1,
                new ArrayList<>()
        );

        lvRecommendations.setAdapter(recommendationsAdapter);

        // Listen when the button is clicked
        buttonRecommend.setOnClickListener(v -> {
            int position = genres.getSelectedItemPosition();
            generateRecommendations(position);
        });

        return view;
    }
    private List<Book> getBooksForGenre(String genre) {
        List<Book> books = booksByGenre.get(genre);
        if (books == null) {
            return new ArrayList<>();
        }
        return books;
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view,
                               int position, long id) {
        // Shows which genre was selected through the spinner (dropdown)
        Toast.makeText(requireContext(),
                genresList[position],
                Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
    private void generateRecommendations(int position) {
        String selectedGenre = genresList[position];
        List<Book> books = getBooksForGenre(selectedGenre);

        // Display the selected genre
        genreRecommend.setText(selectedGenre + " Recommendations");
        recommendationsAdapter.clear();
        if (books.isEmpty()) {
            recommendationsAdapter.add("No Recommendations");
        } else {
            for (Book b : books) {
                recommendationsAdapter.add(b.getTitle());
            }
        }
    }
}