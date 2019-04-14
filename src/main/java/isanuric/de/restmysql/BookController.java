package isanuric.de.restmysql;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/all")
    public Iterable<Book> getBooks() {
       return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Book> getByid(@PathVariable Integer id) {
        Assert.notNull(id, "id could not be null.");
        return bookRepository.findById(id);
    }

    @GetMapping("/add")
    public String addNewBook(
            @RequestParam String name,
            @RequestParam String autor,
            @RequestParam Integer iban) {
        this.addBook(name, autor, iban);
        return "Done";

    }

    @PostMapping("/add")
    public String addNewBookPost(
            @RequestParam String name,
            @RequestParam String autor,
            @RequestParam Integer iban) {
        this.addBook(name, autor, iban);
        return "Done";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        Assert.notNull(id, "id could not be null.");
        bookRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // ~
    // -----------------------------------------------------------------------------------------------------------------
    @GetMapping("/current")
    public Iterable<Book> getCurrentBooks() {
        return bookRepository.findAllExistBooks();
    }


    // ~ Internal Methods
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Add new entity to Repository.
     * @param name
     * @param autor
     * @param iban
     */
    private void addBook(
            @RequestParam String name,
            @RequestParam String autor,
            @RequestParam Integer iban) {
        Assert.notNull(name, "Name could not be null.");
        Assert.notNull(autor, "Autor could not be null.");
        Assert.notNull(iban, "IBAN could not be null.");

        // create new book
        Book book = new Book();
        book.setName(name);
        book.setAutor(autor);
        book.setIban(iban);

        // add to repository
        bookRepository.save(book);
    }

}
