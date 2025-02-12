package jpa.example.repository;

import jpa.example.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Book> rowMapper = (rs, rowNum) -> {
        Book book = new Book();
        book.setId(rs.getLong("id"));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));
        book.setPublicationYear(rs.getInt("publication_year"));
        return book;
    };

    @Override
    public Book save(Book book) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            String sql = "INSERT INTO books (title, author, publication_year) VALUES (?, ?, ?)";
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(
                        sql, Statement.RETURN_GENERATED_KEYS
                );
                ps.setString(1, book.getTitle());
                ps.setString(2, book.getAuthor());
                ps.setInt(3, book.getPublicationYear());
                return ps;
            }, keyHolder);
            book.setId(keyHolder.getKey().longValue());
            return book;
    }

    @Override
    public Book findById(Long id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM books WHERE id = ?",
                rowMapper,
                id
        );
    }


    @Override
    public List<Book> findAll() {
        return jdbcTemplate.query("SELECT * FROM books", rowMapper);
    }

    @Override
    public void update(Book book) {
        jdbcTemplate.update(
                "UPDATE books SET title = ?, author = ?, publication_year = ? WHERE id = ?",
                book.getTitle(),
                book.getAuthor(),
                book.getPublicationYear(),
                book.getId()
        );
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM books WHERE id = ?", id);
    }
}
