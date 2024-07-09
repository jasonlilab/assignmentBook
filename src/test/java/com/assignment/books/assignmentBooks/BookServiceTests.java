package com.assignment.books.assignmentBooks;

import com.assignment.books.assignmentBooks.model.Book;
import com.assignment.books.assignmentBooks.repository.BookRepository;
import com.assignment.books.assignmentBooks.requestModel.ReqBook;
import com.assignment.books.assignmentBooks.service.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class BookServiceTests {

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    public void testGetBookByAuthor() {
        Book mockBook = new Book("112263", "Paul", true);
        List<Book> mockList = new ArrayList<>();
        mockList.add(mockBook);

        when(bookRepository.findByAuthorIgnoreCase(mockBook.getAuthor())).thenReturn(mockList);

        List<Book> result = bookService.getBooks("Paul", null);

        assertNotNull(result);
        assertEquals("112263", result.get(0).getTitle());
        assertEquals("Paul", result.get(0).getAuthor());
        assertTrue(result.get(0).isPublished());
    }

    @Test
    public void testGetBookByPublished() {
        boolean isPublished = true;
        Book mockBook = new Book("TruthsAboutLove", "Betty", isPublished);
        List<Book> mockList = new ArrayList<>();
        mockList.add(mockBook);

        when(bookRepository.findByPublished(isPublished)).thenReturn(mockList);

        List<Book> result = bookService.getBooks(null, isPublished);

        assertNotNull(result);
        assertEquals("TruthsAboutLove", result.get(0).getTitle());
        assertEquals("Betty", result.get(0).getAuthor());
        assertTrue(result.get(0).isPublished());
    }

    @Test
    public void testGetBookByAuthorAndPublished() {
        boolean isPublished = true;
        Book mockBook = new Book("BeautyAndTheBeast", "Sam", isPublished);
        List<Book> mockList = new ArrayList<>();
        mockList.add(mockBook);

        when(bookRepository.findByAuthorIgnoreCaseAndPublished(mockBook.getAuthor(), isPublished)).thenReturn(mockList);

        List<Book> result = bookService.getBooks("Sam", isPublished);

        assertNotNull(result);
        assertEquals("BeautyAndTheBeast", result.get(0).getTitle());
        assertEquals("Sam", result.get(0).getAuthor());
        assertTrue(result.get(0).isPublished());
    }

    @Test
    public void testGetBookByAuthorAndNotPublished() {
        boolean isPublished = false;
        Book mockBook = new Book("TwentiesGirl", "Billy", isPublished);
        List<Book> mockList = new ArrayList<>();
        mockList.add(mockBook);

        when(bookRepository.findByAuthorIgnoreCaseAndPublished(mockBook.getAuthor(), isPublished)).thenReturn(mockList);

        List<Book> result = bookService.getBooks("Billy", isPublished);

        assertNotNull(result);
        assertEquals("TwentiesGirl", result.get(0).getTitle());
        assertEquals("Billy", result.get(0).getAuthor());
        assertFalse(result.get(0).isPublished());
    }

    @Test
    public void testDeleteBook() {
        long bookId = 1L;

        Long result = bookService.deleteBook(bookId);

        assertNull(result);
    }
}
