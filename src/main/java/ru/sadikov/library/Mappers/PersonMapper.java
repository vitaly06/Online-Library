package ru.sadikov.library.Mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.sadikov.library.Models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();
        person.setPersonId(rs.getInt("personId"));
        person.setFullName(rs.getString("fullName"));
        person.setYearOfBirth(rs.getInt("yearOfBirth"));
        return person;
    }
}
