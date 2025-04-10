package com.oopsmails.springboot3.restcrud.book.service;

import com.oopsmails.springboot3.restcrud.book.entity.Book;
import com.oopsmails.springboot3.restcrud.book.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        // Arrange
        Book book1 = createBook(1L, "Book 1", "Author 1");
        Book book2 = createBook(2L, "Book 2", "Author 2");
        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

        // Act
        List<Book> books = bookService.findAll();

        // Assert
        assertThat(books).hasSize(2);
        assertThat(books).containsExactly(book1, book2);
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void testSave() {
        // Arrange
        Book book = createBook(1L, "Book 1", "Author 1");
        when(bookRepository.save(book)).thenReturn(book);

        // Act
        Book savedBook = bookService.save(book);

        // Assert
        assertThat(savedBook).isNotNull();
        assertThat(savedBook.getId()).isEqualTo(1L);
        assertThat(savedBook.getTitle()).isEqualTo("Book 1");
        assertThat(savedBook.getAuthor()).isEqualTo("Author 1");
        verify(bookRepository, times(1)).save(book);
    }

    // Helper method to create Book objects
    private Book createBook(Long id, String title, String author) {
        Book book = new Book();
        book.setId(id);
        book.setTitle(title);
        book.setAuthor(author);
        return book;
    }
}