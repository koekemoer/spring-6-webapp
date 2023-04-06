package guru.springframework.spring6webapp.controllers;

import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : Lou
 * @since : 2023/04/06, Thu, 14:09
 **/
@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService service) {
        this.bookService = service;
    }

    @RequestMapping("/books")
    public String getBooks(Model model) {
        model.addAttribute("books", bookService.findAll());

        // returned String references corresponding HTML page
        return "books";
    }
}
