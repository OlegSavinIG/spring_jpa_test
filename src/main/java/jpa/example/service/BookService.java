package jpa.example.service;

import jpa.example.model.Book;
import jpa.example.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book updateBook(Long id, Book bookUpdate) {
        Book book = getBookById(id);
        book.setTitle(bookUpdate.getTitle());
        book.setAuthor(bookUpdate.getAuthor());
        book.setPublicationYear(bookUpdate.getPublicationYear());
        bookRepository.update(book);
        return book;
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
