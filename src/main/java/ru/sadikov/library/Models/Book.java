package ru.sadikov.library.Models;

import jakarta.validation.constraints.NotNull;

public class Book {
    private int bookId;
    @NotNull
    private String bookName;
    @NotNull
    private String author;
    private int year;
    private int personId;

    public Book(int bookId, String bookName, String author, int year, int personId) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.year = year;
        this.personId = personId;
    }

    public Book(){}

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }
}
