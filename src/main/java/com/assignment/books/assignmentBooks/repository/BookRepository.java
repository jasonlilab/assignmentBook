package com.assignment.books.assignmentBooks.repository;

import com.assignment.books.assignmentBooks.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByTitleIgnoreCaseAndAuthorIgnoreCase(String title, String author);

    List<Book> findByAuthorIgnoreCaseAndPublished(String author, boolean published);

    List<Book> findByAuthorIgnoreCase(String author);

    List<Book> findByPublished(boolean published);

}
