package jpa.example.repository;

import jpa.example.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
class BookRepositoryImplTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private BookRepository bookRepository;
    private Book book;

    @BeforeEach
    void setup() {
        bookRepository = new BookRepositoryImpl(jdbcTemplate);
        jdbcTemplate.execute("DELETE FROM books");
        book = Book.builder()
                .title("Test")
                .author("Author")
                .publicationYear(2000)
                .build();

    }

    @Test
    void shouldSaveAndFindBookById() {
        Book savedBook = bookRepository.save(book);

        assertThat(savedBook.getId()).isNotNull();
        Book foundBook = bookRepository.findById(savedBook.getId());
        assertThat(foundBook).isNotNull();
        assertThat(foundBook.getTitle()).isEqualTo("Test");
    }

    @Test
    void shouldUpdateBook() {
        Book saved = bookRepository.save(book);
        saved.setTitle("New Title");
        bookRepository.update(saved);

        Book updatedBook = bookRepository.findById(saved.getId());
        assertThat(updatedBook).isNotNull();
        assertThat(updatedBook.getTitle()).isEqualTo("New Title");
    }

    @Test
    void shouldDeleteBook() {
        Book saved = bookRepository.save(book);
        bookRepository.deleteById(saved.getId());

        assertThat(bookRepository.findAll()).isEmpty();
    }

    @Test
    void shouldFindAllBooks() {
        bookRepository.save(book);
        bookRepository.save(Book.builder()
                .title("Title2")
                .author("Author2")
                .publicationYear(2001)
                .build());

        List<Book> books = bookRepository.findAll();
        assertThat(books).hasSize(2);
    }
}