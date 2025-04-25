package myy803.springboot.bookstore;

import myy803.springboot.bookstore.dao.UserDAO;
import myy803.springboot.bookstore.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class UserDAOTest {

    @Autowired
    private UserDAO userDAO;

    @Test
    public void testFindByUsername() {
        // Given
        String username = "elena";

        // When
        Optional<User> optionalUser = userDAO.findByUsername(username);

        // Then
        assertTrue(optionalUser.isPresent()); // Check if user with the specified username exists
        User user = optionalUser.get();
        assertEquals(username, user.getUsername()); // Verify that the username of the returned user matches the expected value
        // You can add more assertions to verify other properties of the user if needed
    }
}
