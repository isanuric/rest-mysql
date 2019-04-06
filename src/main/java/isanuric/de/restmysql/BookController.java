package isanuric.de.restmysql;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/add")
    public String addNewBook(
            @RequestParam String name,
            @RequestParam String autor,
            @RequestParam Integer iban) {

        // create new book
        Book book = new Book();
        book.setName(name);
        book.setAutor(autor);
        book.setIban(iban);

        // add to repository
        bookRepository.save(book);

        return "Done.";
    }

}
