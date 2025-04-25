package myy803.springboot.bookstore;

import myy803.springboot.bookstore.dao.UserProfileMapper;
import myy803.springboot.bookstore.model.UserProfile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class UserProfileMapperTest {

    @Autowired
    private UserProfileMapper userProfileMapper;

    @Test
    public void testFindByUsername() {
        // Given
        String username = "elena";

        // When
        Optional<UserProfile> optionalUserProfile = userProfileMapper.findByUsername(username);

        // Then
        assertTrue(optionalUserProfile.isPresent()); // Check if user profile with the specified username exists
        UserProfile userProfile = optionalUserProfile.get();
        assertEquals(username, userProfile.getUsername()); // Verify that the username of the returned user profile matches the expected value
        // You can add more assertions to verify other properties of the user profile if needed
    }
}

