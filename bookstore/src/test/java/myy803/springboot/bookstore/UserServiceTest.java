package myy803.springboot.bookstore;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import myy803.springboot.bookstore.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import myy803.springboot.bookstore.dao.UserDAO;
import myy803.springboot.bookstore.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserServiceTest {

    @Test
    public void testSaveUser() {
        // Create a mock BCryptPasswordEncoder
        BCryptPasswordEncoder passwordEncoder = mock(BCryptPasswordEncoder.class);
        // Mock the encode method to return the original password
        when(passwordEncoder.encode(anyString())).thenAnswer(invocation -> invocation.getArgument(0));

        // Create a mock UserDAO
        UserDAO userDAO = mock(UserDAO.class);

        // Create a UserServiceImpl instance with the mock UserDAO and BCryptPasswordEncoder
        UserServiceImpl userService = new UserServiceImpl(passwordEncoder, userDAO);

        // Create a user to save
        User user = new User();
        user.setUsername("elena");
        user.setPassword("aa");

        // Call the saveUser method
        userService.saveUser(user);

        // Verify that the save method of the mock UserDAO was called with the correct user
        verify(userDAO).save(user);
    }

    @Test
    public void testIsUserPresent() {
        // Create a mock UserDAO
        UserDAO userDAO = mock(UserDAO.class);

        // Create a UserServiceImpl instance with the mock UserDAO
        UserServiceImpl userService = new UserServiceImpl(mock(BCryptPasswordEncoder.class), userDAO);

        // Create a user
        User user = new User();
        user.setUsername("existinguser");

        // Configure the mock UserDAO to return an optional containing the user
        when(userDAO.findByUsername(user.getUsername())).thenReturn(Optional.of(user));

        // Call the isUserPresent method with the existing user
        assertTrue(userService.isUserPresent(user));

        // Create a user that doesn't exist
        User nonExistingUser = new User();
        nonExistingUser.setUsername("nonexistinguser");

        // Configure the mock UserDAO to return an empty optional
        when(userDAO.findByUsername(nonExistingUser.getUsername())).thenReturn(Optional.empty());

        // Call the isUserPresent method with the non-existing user
        assertFalse(userService.isUserPresent(nonExistingUser));
    }

    @Test
    public void testLoadUserByUsername() {
        // Create a mock UserDAO
        UserDAO userDAO = mock(UserDAO.class);

        // Create a UserServiceImpl instance with the mock UserDAO
        UserServiceImpl userService = new UserServiceImpl(mock(BCryptPasswordEncoder.class), userDAO);

        // Create a user
        User user = new User();
        user.setUsername("elena");

        // Configure the mock UserDAO to return an optional containing the user
        when(userDAO.findByUsername(user.getUsername())).thenReturn(Optional.of(user));

        // Call the loadUserByUsername method with the username of the existing user
        UserDetails userDetails = userService.loadUserByUsername(user.getUsername());

        // Verify that the UserDetails returned matches the user
        assertEquals(user.getUsername(), userDetails.getUsername());
    }

    // Additional tests for other methods can be added here
}

