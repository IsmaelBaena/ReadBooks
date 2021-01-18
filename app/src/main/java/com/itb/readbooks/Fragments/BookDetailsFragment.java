package com.itb.readbooks.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.itb.readbooks.Models.BookModel;
import com.itb.readbooks.R;

public class BookDetailsFragment extends Fragment {
    BookModel book;
    TextView title, author, status, description;
    RatingBar puntuation;
    FloatingActionButton update;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        book = (BookModel) requireArguments().get("book");
    }

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.book_details, container, false);

        title = v.findViewById(R.id.textView_DetailsTitle);
        author = v.findViewById(R.id.textView_detailsAuthor);
        status = v.findViewById(R.id.textView_detailsStatus);
        description = v.findViewById(R.id.textView_detailsDescription);
        puntuation = v.findViewById(R.id.ratingBar_detailsPuntuation);
        update = v.findViewById(R.id.floatingActionButton_update);

        title.setText(book.getTitle());
        author.setText(book.getAuthor());
        description.setText(book.getDescription());
        puntuation.setProgress(book.getPuntuation());

        switch (book.getStatus()) {
            case 0:
                status.setText("Dropped");
                break;
            case 1:
                status.setText("Wanted to read");
                break;
            case 2:
                status.setText("Reading");
                break;
            case 3:
                status.setText("Read");
                break;
        }

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections listToAddBook = BookDetailsFragmentDirections.actionBookDetailsFragmentToAddBookFragment(book, (int) requireArguments().get("position"));
                Navigation.findNavController(v).navigate(listToAddBook);
            }
        });

        return v;
    }
}
