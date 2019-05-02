package isanuric.de.restmysql;


import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.DependsOn;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BookControllerTest {

    private static final String JSON_CHARSET_UTF_8 = "application/json;charset=UTF-8";

    @Autowired
    protected WebTestClient webTestClient;

    @Autowired
    private BookRepository bookRepository;

    @Before
    public void setup() {
        bookRepository.save(new Book("setuptestuser", "setuptestuser", 1234, 1));
    }

    @After
    public void tearDown() {
        bookRepository.deleteAll();
    }

    @Test
    public void getBooks() {
        webTestClient.get()
                .uri("/books/all")
                .exchange()
                .expectHeader().valueEquals("Content-Type", "application/json;charset=UTF-8")
                .expectStatus().isOk()
                // TODO: 02/05/2019 use json
                .expectBodyList(String.class).consumeWith(consumer ->
                assertTrue(consumer.getResponseBody().get(0).contains("\"id\":1"))
        );
    }

    @Test
    public void getByid() {
        webTestClient.get()
                .uri("/books/1")
                .exchange()
                .expectHeader().valueEquals("Content-Type", JSON_CHARSET_UTF_8)
                .expectStatus().isOk()
                // TODO: 02/05/2019 use json
                .expectBodyList(String.class).consumeWith(consumer ->
                assertTrue(consumer.getResponseBody().get(0).contains("\"name\":\"setuptestuser\""))
        );
    }

    @Test
    public void addNewBookPost_POST() {
        webTestClient.post()
                .uri("/books/add?name=unittestPost&autor=authtest&iban=111111")
                .exchange()
                .expectHeader().valueEquals("Content-Type", "text/plain;charset=UTF-8")
                .expectStatus().isOk()
                // TODO: 02/05/2019 use json
                .expectBodyList(String.class).consumeWith(consumer ->
                assertTrue(consumer.getResponseBody().get(0).contains("Done"))
        );
    }

    @Test
    @DependsOn("addNewBookPost_POST")
    public void deleteUser() {
        webTestClient.post()
                .uri("/books/delete/1")
                .exchange()
                .expectHeader().valueEquals("Content-Type", JSON_CHARSET_UTF_8)
                .expectStatus().isOk()
                // TODO: 02/05/2019 use json
                .expectBodyList(String.class).consumeWith(consumer ->
                assertTrue(consumer.getResponseBody().get(0).contains("Done"))
        );
    }
}