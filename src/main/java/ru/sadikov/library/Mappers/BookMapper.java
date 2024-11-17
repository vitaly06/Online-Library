package ru.sadikov.library.Mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.sadikov.library.Models.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            Book book = new Book();
            book.setBookId(rs.getInt("bookId"));
            book.setBookName(rs.getString("bookName"));
            book.setAuthor(rs.getString("author"));
            book.setYear(rs.getInt("year"));
            book.setPersonId(rs.getInt("personId"));
            return book;
        } catch (Exception e) {
            return new Book();
        }
    }
}
