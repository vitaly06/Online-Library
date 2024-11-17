package ru.sadikov.library.Controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.sadikov.library.DAO.PeopleDAO;
import ru.sadikov.library.Models.Book;
import ru.sadikov.library.Models.Person;
import ru.sadikov.library.util.PersonValidator;

import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PeopleDAO peopleDAO;
    private final PersonValidator personValidator;

    @Autowired
    public PeopleController(PeopleDAO peopleDAO, PersonValidator personValidator) {
        this.peopleDAO = peopleDAO;
        this.personValidator = personValidator;
    }
    // Все люди
    @GetMapping
    public String allPeople(Model model) {
        model.addAttribute("people", peopleDAO.getAllPersons());
        return "people/allPeople";
    }
    // Страница создания человека
    @GetMapping("/create")
    public String createPerson(Model model) {
        model.addAttribute("person", new Person());
        return "people/createPeople";
    }
    // создание человека
    @PostMapping("/add")
    public String addPerson(@ModelAttribute("person") @Valid Person person,
                            BindingResult bindingResult){
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "people/createPeople";
        }
        peopleDAO.addPerson(person);
        return "redirect:/people";
    }
    // Информация о человеке
    @GetMapping("/{id}")
    public String getPersonById(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", peopleDAO.getPersonById(id));
        List<Book> books = peopleDAO.getBookByPersonId(id);
        if (books.size() > 0) {
            model.addAttribute("books", books);
        } else{
            model.addAttribute("books", null);
        }
        return "people/showPerson";
    }

    // Удаление человека
    @PostMapping("/delete/{id}")
    public String deletePerson(@PathVariable("id") int id) {
        peopleDAO.deletePersonById(id);
        return "redirect:/people";
    }

    // страница изменения человека
    @GetMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", peopleDAO.getPersonById(id));
        return "people/editPerson";
    }
    // изменение человека
    @PostMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") int id,
                             @ModelAttribute("person") Person person) {
        peopleDAO.updatePerson(person, id);
        return "redirect:/people";
    }
}
