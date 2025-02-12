package jpa.example.repository;


import jpa.example.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book save(Book book);

    Book findById(Long id);

    List<Book> findAll();

    void update(Book book);

    void deleteById(Long id);
}
