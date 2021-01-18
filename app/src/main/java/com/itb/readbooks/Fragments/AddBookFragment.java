package com.itb.readbooks.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import com.itb.readbooks.Models.BookModel;
import com.itb.readbooks.R;

public class AddBookFragment extends Fragment {
    EditText title, author, description;
    Spinner status;
    public RatingBar ratingBar;
    Button add;
    BookModel book;
    boolean isUpdate = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            book = (BookModel) requireArguments().get("book");
            isUpdate = true;
        }
        return inflater.inflate(R.layout.add_book, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        title = view.findViewById(R.id.editText_title);
        author = view.findViewById(R.id.editText_author);
        description = view.findViewById(R.id.editText_description);
        status = view.findViewById(R.id.spinner_status);
        add = view.findViewById(R.id.button_add);
        ratingBar = view.findViewById(R.id.ratingBar_add);

        if (isUpdate) {
            title.setText(book.getTitle());
            author.setText(book.getAuthor());
            description.setText(book.getDescription());
            ratingBar.setProgress(book.getPuntuation());
            add.setText("Update");
            status.setSelection(book.getStatus());
        }

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!title.getText().toString().isEmpty() && !author.getText().toString().isEmpty()) {
                    BookModel book = new BookModel(title.getText().toString(),author.getText().toString(), description.getText().toString());
                    switch (status.getSelectedItem().toString()) {
                        case "Dropped":
                            book.statusIsDropped();
                            break;
                        case "Wanted to read":
                            book.statusIsWantToRead();
                            break;
                        case "Reading":
                            book.statusIsReading();
                            break;
                        case "Read":
                            book.statusIsRead();
                            book.setPuntuation(ratingBar.getProgress());
                            break;
                    }
                    NavDirections addBokktoList;
                    if (isUpdate) addBokktoList = AddBookFragmentDirections.actionAddBookFragmentToBookListFragment(book, (int) requireArguments().get("position"));
                    else  addBokktoList = AddBookFragmentDirections.actionAddBookFragmentToBookListFragment(book, -1);
                    Navigation.findNavController(v).navigate(addBokktoList);
                } else {
                    Toast.makeText(v.getContext(), "Make sure all the important gaps (*) are filled", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
