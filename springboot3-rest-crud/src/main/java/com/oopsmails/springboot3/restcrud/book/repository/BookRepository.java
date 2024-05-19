package com.oopsmails.springboot3.restcrud.book.repository;

import com.oopsmails.springboot3.restcrud.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
