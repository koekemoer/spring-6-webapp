package guru.springframework.spring6webapp.controllers;

import guru.springframework.spring6webapp.services.AuthorService;
import guru.springframework.spring6webapp.services.AuthorServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : Lou
 * @since : 2023/04/06, Thu, 14:44
 **/
@Controller
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @RequestMapping("/authors")
    public String getAuthors(Model model) {
        model.addAttribute("authors", authorService.findAll());

        // returned String references corresponding HTML page
        return "author";
    }

}
