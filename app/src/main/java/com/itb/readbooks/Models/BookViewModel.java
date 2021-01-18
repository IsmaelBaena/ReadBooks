package com.itb.readbooks.Models;

import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;

public class BookViewModel extends ViewModel {

    public List<BookModel> booksList = new ArrayList<>();

    public BookViewModel() {
        for (int i = 0; i < 10; i++) {
            booksList.add(new BookModel("Book #" + 1, "Emilio " + 1 + "o", "ERROR: DESCRIPTION MISSING",(int) (Math.random() * 4)));
            if (booksList.get(i).getStatus() == 3) booksList.get(i).setPuntuation((int) (Math.random() * 11));
        }
    }
}
