package com.assignment.books.assignmentBooks.controllers;

import com.assignment.books.assignmentBooks.model.Book;
import com.assignment.books.assignmentBooks.requestModel.ReqBook;
import com.assignment.books.assignmentBooks.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @PostMapping("")
    public ResponseEntity<Long> createBook(@Validated @RequestBody ReqBook reqBook) {
        try {
            logger.info("create book: {}", reqBook.toString());

            Long newBookId = bookService.createBook(reqBook);

            return ResponseEntity.status(HttpStatus.CONFLICT).body(newBookId != null ? newBookId : (long) -1);
        } catch (Exception e) {
            logger.error("createBook exception: ", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((long) -1);
    }

    @GetMapping("")
    public ResponseEntity<List<Book>> getBooks(@RequestParam(required = false) String author,
                                               @RequestParam(required = false) Boolean published) {
        try {
            logger.info("get books: {}, {}", author, published);

            List<Book> result = bookService.getBooks(author, published);
            if (!result.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(result);
            }
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(result);
        } catch (Exception e) {
            logger.error("getBooks exception: ", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteBook(@PathVariable("id") long id) {
        try {
            logger.info("delete book: {}", id);

            Long removeId = bookService.deleteBook(id);

            if (removeId != null) {
                return ResponseEntity.status(HttpStatus.OK).body(id);
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id);

        } catch (Exception e) {
            logger.error("deleteBook exception ", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((long) -1);
    }

}
