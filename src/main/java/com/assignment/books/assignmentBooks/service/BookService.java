package com.assignment.books.assignmentBooks.service;

import com.assignment.books.assignmentBooks.model.Book;
import com.assignment.books.assignmentBooks.requestModel.ReqBook;

import java.util.List;

public interface BookService {

    Long createBook(ReqBook reqBook);

    List<Book> getBooks(String author, Boolean published);

    Long deleteBook(long id);

}
