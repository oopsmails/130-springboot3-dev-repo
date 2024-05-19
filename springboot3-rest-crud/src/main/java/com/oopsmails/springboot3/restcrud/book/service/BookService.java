package com.oopsmails.springboot3.restcrud.book.service;

import com.oopsmails.springboot3.restcrud.book.entity.Book;
import com.oopsmails.springboot3.restcrud.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }
}

