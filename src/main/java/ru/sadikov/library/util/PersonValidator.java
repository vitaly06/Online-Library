package ru.sadikov.library.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.sadikov.library.DAO.PeopleDAO;
import ru.sadikov.library.Models.Person;

@Component
public class PersonValidator implements Validator {
    private PeopleDAO peopleDAO;
    @Autowired
    public PersonValidator(PeopleDAO peopleDAO) {
        this.peopleDAO = peopleDAO;
    }
    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if (peopleDAO.getPersonByFullName(person.getFullName()).isPresent()){
            errors.rejectValue("fullName", null, "This fullName already exists");
        }
    }
}
