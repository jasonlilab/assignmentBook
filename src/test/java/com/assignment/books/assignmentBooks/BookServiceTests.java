package com.assignment.books.assignmentBooks;

import com.assignment.books.assignmentBooks.model.Book;
import com.assignment.books.assignmentBooks.repository.BookRepository;
import com.assignment.books.assignmentBooks.requestModel.ReqBook;
import com.assignment.books.assignmentBooks.service.BookService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BookServiceTests {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    public void testCreateBook() {
        long bookId = 1L;
        ReqBook mockBook = new ReqBook("Catch22", "Sally", true);

        Mockito.when(bookService.createBook(mockBook)).thenReturn(bookId);

        List<Book> result = bookRepository.findByAuthorIgnoreCaseAndPublished("Sally", true);

        assertNotNull(result);
        assertEquals(bookId, result.get(0).getId());
        assertEquals("Catch22", result.get(0).getTitle());
        assertEquals("Sally", result.get(0).getAuthor());
        assertTrue(result.get(0).isPublished());
    }

    @Test
    public void testGetBookByAuthor() {
        Book mockBook = new Book("112263", "Paul", true);

        Mockito.when(bookRepository.save(mockBook)).thenReturn(mockBook);

        List<Book> result = bookService.getBooks("Paul", null);

        assertNotNull(result);
        assertEquals("112263", result.get(0).getTitle());
        assertEquals("Paul", result.get(0).getAuthor());
        assertTrue(result.get(0).isPublished());
    }

    @Test
    public void testGetBookByPublished() {
        long bookId = 1L;
        Book mockBook = new Book("TruthsAboutLove", "Betty", true);

        Mockito.when(bookRepository.save(mockBook)).thenReturn(mockBook);

        List<Book> result = bookService.getBooks(null, true);

        assertNotNull(result);
        assertEquals("TruthsAboutLove", result.get(0).getTitle());
        assertEquals("Betty", result.get(0).getAuthor());
        assertTrue(result.get(0).isPublished());
    }

    @Test
    public void testGetBookByAuthorAndPublished() {
        long bookId = 1L;
        Book mockBook = new Book("BeautyAndTheBeast", "Sam", true);

        Mockito.when(bookRepository.save(mockBook)).thenReturn(mockBook);

        List<Book> result = bookService.getBooks("Sam", true);

        assertNotNull(result);
        assertEquals("BeautyAndTheBeast", result.get(0).getTitle());
        assertEquals("Sam", result.get(0).getAuthor());
        assertTrue(result.get(0).isPublished());
    }

    @Test
    public void testGetBookByAuthorAndNotPublished() {
        long bookId = 1L;
        Book mockBook = new Book("TwentiesGirl", "Billy", false);

        Mockito.when(bookRepository.save(mockBook)).thenReturn(mockBook);

        List<Book> result = bookService.getBooks("Billy", false);

        assertNotNull(result);
        assertEquals("TwentiesGirl", result.get(0).getTitle());
        assertEquals("Billy", result.get(0).getAuthor());
        assertFalse(result.get(0).isPublished());
    }

    @Test
    public void testDeleteBook() {
        long bookId = 1L;
        Book mockBook = new Book("20thCenturyGhosts", "Joe", true);

        Mockito.when(bookRepository.save(mockBook)).thenReturn(mockBook);

        Long result = bookService.deleteBook(bookId);

        assertNotNull(result);
        assertEquals(bookId, result);
    }
}
