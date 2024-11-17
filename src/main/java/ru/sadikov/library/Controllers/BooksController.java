package ru.sadikov.library.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sadikov.library.DAO.BookDAO;
import ru.sadikov.library.DAO.PeopleDAO;
import ru.sadikov.library.Models.Book;
import ru.sadikov.library.Models.Person;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDAO bookDAO;
    private final PeopleDAO peopleDAO;

    public BooksController(BookDAO bookDAO, PeopleDAO peopleDAO) {
        this.bookDAO = bookDAO;
        this.peopleDAO = peopleDAO;
    }

    @GetMapping
    public String allBooks(Model model) {
        model.addAttribute("books", bookDAO.getAllBooks());
        return "books/allBooks";
    }

    // Страница добавления книги
    @GetMapping("/create")
    public String createBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("people", peopleDAO.getAllPersons());
        return "books/createBook";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute("book") Book book) {
        bookDAO.addBook(book);
        return "redirect:/books";
    }

    // Страница книги
    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model model) {
        Book book = bookDAO.getBookById(id);
        model.addAttribute("book", book);
        System.out.println(book.getPersonId());
        if (book.getPersonId() == 0){
            model.addAttribute("nullPerson", new Person());
        } else{
            model.addAttribute("getBook", peopleDAO.getPersonById(book.getPersonId()));
        }
        model.addAttribute("people", peopleDAO.getAllPersons());

        return "books/showBook";
    }

    // Удаление книги
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookDAO.deleteBookById(id);
        return "redirect:/books";
    }

    // Назначение книги человек
    @PostMapping("/addPersonToBook/{id}")
    public String addPersonToBook(@PathVariable("id") int id, @ModelAttribute("nullPerson") Person person) {
        bookDAO.addPersonToBook(id, person);
        return "redirect:/books";
    }
    // Освобождение книги
    @PostMapping("/freeBook/{id}")
    public String freeBook(@PathVariable("id") int id) {
        bookDAO.freeBook(id);
        return "redirect:/books";
    }
    // Изменение книги
    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.getBookById(id));
        return "books/editBook";
    }

    @PostMapping("/edit/{id}")
    public String editBook(@ModelAttribute("book") Book book, @PathVariable("id") int id) {
        bookDAO.editBook(book, id);
        return "redirect:/books";
    }
}
