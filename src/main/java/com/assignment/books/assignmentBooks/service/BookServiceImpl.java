package com.assignment.books.assignmentBooks.service;

import com.assignment.books.assignmentBooks.model.Book;
import com.assignment.books.assignmentBooks.repository.BookRepository;
import com.assignment.books.assignmentBooks.requestModel.ReqBook;
import io.micrometer.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Long createBook(ReqBook reqBook) {
        try {
            Book b = bookRepository.findByTitleIgnoreCaseAndAuthorIgnoreCase(reqBook.getTitle(), reqBook.getAuthor());
            if (b == null) {
                Book newBook = new Book(reqBook.getTitle(), reqBook.getAuthor(),
                        reqBook.isPublished());
                newBook = bookRepository.save(newBook);
                return newBook.getId();
            }
        } catch (Exception e) {
            logger.error("createBook exception: ", e);
        }
        return null;
    }

    @Override
    public List<Book> getBooks(String author, Boolean published) {
        List<Book> result = new ArrayList<>();
        try {
            if (StringUtils.isNotBlank(author) && published != null) {
                result.addAll(bookRepository.findByAuthorIgnoreCaseAndPublished(author, published));
            } else if (StringUtils.isNotBlank(author)) {
                result.addAll(bookRepository.findByAuthorIgnoreCase(author));
            } else if (published != null) {
                result.addAll(bookRepository.findByPublished(published));
            } else {
                result.addAll(bookRepository.findAll());
            }
        } catch (Exception e) {
            logger.error("getBooks exception: ", e);
        }
        return result;
    }

    @Override
    public Long deleteBook(long id) {
        try {
            Optional<Book> b = bookRepository.findById(id);
            if (b.isPresent()) {
                bookRepository.deleteById(id);
                return id;
            }
        } catch (Exception e) {
            logger.error("deleteBook exception ", e);
        }
        return null;
    }
}
