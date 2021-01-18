package com.itb.readbooks.Adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.itb.readbooks.Models.BookModel;
import com.itb.readbooks.R;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookHolder> {
    private List<BookModel> books;
    private OnItemClickListener itemClickListener;

    public interface  OnItemClickListener {
        void onItemClick(BookModel bookModel, int position);
    }

    public BookAdapter(List<BookModel> books, OnItemClickListener itemClickListener) {
        this.books = books;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_list_item, parent, false);
        return new BookHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BookHolder holder, int position) {
        holder.bind(books.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public static class BookHolder extends RecyclerView.ViewHolder {
        TextView title, author, currentStatus;
        RatingBar rating;

        public BookHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView_list_item_title);
            author = itemView.findViewById(R.id.textView_list_item_author);
            currentStatus = itemView.findViewById(R.id.textView_list_item_current_status);
            rating = itemView.findViewById(R.id.ratingBar);
        }

        @SuppressLint({"SetTextI18n", "ResourceAsColor"})
        public void bind(final BookModel book, final OnItemClickListener listener) {
            title.setText(book.getTitle());
            author.setText(book.getAuthor());
            switch (book.getStatus()) {
                case 0:
                    currentStatus.setText("Dropped");
                    currentStatus.setTextColor(Color.parseColor("#DA0303"));
                    rating.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    currentStatus.setText("Wanted to Read");
                    currentStatus.setTextColor(Color.parseColor("#3F51B5"));
                    rating.setVisibility(View.INVISIBLE);
                    break;
                case 2:
                    currentStatus.setText("Reading");
                    currentStatus.setTextColor(Color.parseColor("#D5C108"));
                    rating.setVisibility(View.INVISIBLE);
                    break;
                case 3:
                    currentStatus.setText("Read");
                    currentStatus.setTextColor(Color.parseColor("#36BC06"));
                    rating.setProgress(book.getPuntuation());
                    break;
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(book, getAdapterPosition());
                }
            });
        }
    }
}
