package com.itb.readbooks.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.itb.readbooks.Adapter.BookAdapter;
import com.itb.readbooks.Models.BookModel;
import com.itb.readbooks.Models.BookViewModel;
import com.itb.readbooks.R;

public class BookListFragment  extends Fragment {
    RecyclerView recyclerView;
    public BookViewModel bookViewModel;
    FloatingActionButton addButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bookViewModel = new ViewModelProvider(this).get(BookViewModel.class);
        if (getArguments() != null) {
            int position = (int) requireArguments().get("position");
            if (0 > position) bookViewModel.booksList.add((BookModel) requireArguments().get("book"));
            else bookViewModel.booksList.set(position, (BookModel) requireArguments().get("book"));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.book_list, container, false);

        BookAdapter adapter = new BookAdapter(bookViewModel.booksList, new BookAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BookModel bookModel, int position) {
                NavDirections listToAddBook = BookListFragmentDirections.actionBookListFragmentToBookDetailsFragment(bookViewModel.booksList.get(position), position);
                Navigation.findNavController(v).navigate(listToAddBook);
            }
        });

        recyclerView = v.findViewById(R.id.recycleView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));

        addButton = v.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections listToAddBook = BookListFragmentDirections.actionBookListFragmentToAddBookFragment();
                Navigation.findNavController(v).navigate(listToAddBook);
            }
        });

        return v;
    }
}
