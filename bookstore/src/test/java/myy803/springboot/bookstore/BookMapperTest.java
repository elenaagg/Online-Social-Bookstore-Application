package myy803.springboot.bookstore;

import myy803.springboot.bookstore.dao.BookMapper;
import myy803.springboot.bookstore.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class BookMapperTest {

    @Autowired
    private BookMapper bookMapper;

    @Test
    public void testFindByUsername() {
        // Given
        String username = "elena";

        // When
        List<Book> books = bookMapper.findByUsername(username);

        // Then
        assertEquals(2, books.size()); // Assuming there are two books associated with the username "john"
        // You can add more assertions to verify the content of the returned list
    }
}

