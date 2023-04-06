package guru.springframework.spring6webapp.services;

import guru.springframework.spring6webapp.domain.Book;

/**
 * @author : Lou
 * @since : 2023/04/06, Thu, 14:01
 **/
public interface BookService {

    Iterable<Book> findAll();
}
