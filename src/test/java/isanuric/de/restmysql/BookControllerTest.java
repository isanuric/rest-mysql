package isanuric.de.restmysql;


import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BookControllerTest {

    @Autowired
    protected WebTestClient webTestClient;

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
    public void  getByid() {
        webTestClient.get()
                .uri("/books/1")
                .exchange()
                .expectHeader().valueEquals("Content-Type", "application/json;charset=UTF-8")
                .expectStatus().isOk()
                // TODO: 02/05/2019 use json
                .expectBodyList(String.class).consumeWith(consumer ->
                assertTrue(consumer.getResponseBody().get(0).contains("\"id\":1"))
        );
    }

    @Test
    public void  addNewBook() {
    }

    @Test
    public void addNewBookPost() {
    }

    @Test
    public void deleteUser() {
    }

    @Test
    public void getCurrentBooks() {
    }
}