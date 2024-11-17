package ru.sadikov.library.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.sadikov.library.Mappers.BookMapper;
import ru.sadikov.library.Mappers.PersonMapper;
import ru.sadikov.library.Models.Book;
import ru.sadikov.library.Models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PeopleDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PeopleDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public PeopleDAO(){
        this.jdbcTemplate = null;
    }

    public void addPerson(Person person) {
        jdbcTemplate.update("INSERT INTO Person(fullName,yearOfBirth) VALUES(?, ?)",
                person.getFullName(), person.getYearOfBirth());
    }

    public List<Person> getAllPersons() {
        return jdbcTemplate.query("SELECT * FROM Person", new PersonMapper());
    }

    public Person getPersonById(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE personId = ?", new Object[]{id}, new PersonMapper())
                .stream().findAny().orElse(null);
    }

    public void deletePersonById(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE personId = ?", new Object[]{id});
    }

    public void updatePerson(Person person, int id) {
        jdbcTemplate.update("UPDATE Person SET fullName = ?, yearOfBirth = ? WHERE personId = ?",
                person.getFullName(), person.getYearOfBirth(), id);
    }

    public List<Book> getBookByPersonId(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE personId = ?",
                new Object[]{id}, new BookMapper());
    }

    public Optional<Person> getPersonByFullName(String fullName) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE fullName = ?",
                new Object[]{fullName}, new PersonMapper()).stream().findAny();
    }
}
