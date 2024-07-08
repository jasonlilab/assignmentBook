package com.assignment.books.assignmentBooks.requestModel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ReqBook {

    @NotBlank(message = "The title is required.")
    @Size(min = 1, max = 50, message = "The title must from 1 to 50 characters.")
//    @Pattern(regexp = "^[A-Za-z0-9]$")
//    @Pattern(regexp = "[^a-zA-Z0-9]+")
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    private String title;

    @Size(min = 1, max = 50, message = "The author must from 1 to 50 characters.")
//    @Pattern(regexp = "[^a-zA-Z0-9]+")
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    private String author;

    private boolean published = false;

    public ReqBook() {
    }

    public ReqBook(String title, String author, boolean published) {
        this.title = title;
        this.author = author;
        this.published = published;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isPublished() {
        return published;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    @Override
    public String toString() {
        return "ReqBook [title=" + title + ", author=" + author + ", published="
                + published + "]";
    }
}
