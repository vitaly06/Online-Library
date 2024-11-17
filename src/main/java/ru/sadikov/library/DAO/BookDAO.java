package ru.sadikov.library.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.sadikov.library.Mappers.BookMapper;
import ru.sadikov.library.Models.Book;
import ru.sadikov.library.Models.Person;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public BookDAO(){
        this.jdbcTemplate = null;
    }

    public List<Book> getAllBooks(){
        return jdbcTemplate.query("SELECT * FROM Book", new BookMapper());
    }

    public void addBook(Book book){
        jdbcTemplate.update("INSERT INTO Book(bookName, author, year) VALUES(?, ?, ?)",
                book.getBookName(), book.getAuthor(), book.getYear());
    }

    public Book getBookById(int id){
        return jdbcTemplate.query("SELECT * FROM Book WHERE bookId = ?", new Object[]{id}, new BookMapper())
                .stream().findAny().orElse(null);
    }

    public void deleteBookById(int id){
        jdbcTemplate.update("DELETE FROM Book WHERE bookId = ?", id);
    }

    public void addPersonToBook(int id, Person person) {
        jdbcTemplate.update("UPDATE Book SET personId = ? WHERE bookId = ?",
                person.getPersonId(), id);
    }

    public void freeBook(int id){
        jdbcTemplate.update("UPDATE Book SET personId = null WHERE bookId = ?", id);
    }

    public void editBook(Book book, int id){
        jdbcTemplate.update("UPDATE Book SET bookName = ?, author = ?, year = ? WHERE bookId = ?",
                book.getBookName(), book.getAuthor(), book.getYear(), id);
    }
}
