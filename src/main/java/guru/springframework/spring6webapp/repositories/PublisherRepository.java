package guru.springframework.spring6webapp.repositories;

import guru.springframework.spring6webapp.domain.Publisher;
import org.springframework.data.repository.CrudRepository;

/**
 * @author : Lou
 * @since : 2023/04/06, Thu, 09:49
 **/
public interface PublisherRepository extends CrudRepository<Publisher, Long> {
}
