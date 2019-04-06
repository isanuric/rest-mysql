package isanuric.de.restmysql;

import org.springframework.data.repository.CrudRepository;

/**
 * Interface for generic CRUD operations on a repository for a specific type.
 */

public interface  BookRepository extends CrudRepository<Book, Integer> {

}
