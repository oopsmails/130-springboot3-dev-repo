package com.oopsmails.springboot3.restcrud.book.repository;

import com.oopsmails.springboot3.restcrud.book.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BookRepositoryTests {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testCreateAndFindBook() {
        Book book = new Book();
        book.setTitle("Spring Boot with H2");
        book.setAuthor("John Doe");

        book = bookRepository.save(book);

        Optional<Book> foundBook = bookRepository.findById(book.getId());
        assertThat(foundBook).isPresent();
        assertThat(foundBook.get().getTitle()).isEqualTo("Spring Boot with H2");
        assertThat(foundBook.get().getAuthor()).isEqualTo("John Doe");
    }
}
