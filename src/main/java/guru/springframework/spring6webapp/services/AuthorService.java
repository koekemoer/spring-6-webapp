package guru.springframework.spring6webapp.services;

import guru.springframework.spring6webapp.domain.Author;

/**
 * @author : Lou
 * @since : 2023/04/06, Thu, 14:45
 **/
public interface AuthorService {

    Iterable<Author> findAll();
}
