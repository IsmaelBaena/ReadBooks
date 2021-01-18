package com.itb.readbooks.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class BookModel implements Parcelable {
    //El status es un numero entre 0 y 3 donde 0 = dropped, 1 = Wanted to read, 2 = reading, 3 = read
    private int puntuation, status;
    private String title, author, description;

    public BookModel( String title, String author, String description) {
        this.title = title;
        this.author = author;
        this.description = description;
    }

    public BookModel( String title, String author, String description, int status) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.status = status;
    }

    protected BookModel(Parcel in) {
        puntuation = in.readInt();
        status = in.readInt();
        title = in.readString();
        author = in.readString();
        description = in.readString();
    }

    public static final Creator<BookModel> CREATOR = new Creator<BookModel>() {
        @Override
        public BookModel createFromParcel(Parcel in) {
            return new BookModel(in);
        }

        @Override
        public BookModel[] newArray(int size) {
            return new BookModel[size];
        }
    };

    public int getPuntuation() {
        return puntuation;
    }

    public void setPuntuation(int puntuation) {
        this.puntuation = puntuation;
    }

    public void statusIsDropped() {status = 0;}

    public void statusIsWantToRead() {status = 1;}

    public void statusIsReading() {status = 2;}

    public void statusIsRead() {status = 3;}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(puntuation);
        dest.writeInt(status);
        dest.writeString(title);
        dest.writeString(author);
        dest.writeString(description);
    }
}
