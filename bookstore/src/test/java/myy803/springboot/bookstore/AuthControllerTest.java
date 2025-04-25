package myy803.springboot.bookstore;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import myy803.springboot.bookstore.controller.AuthController;
import myy803.springboot.bookstore.model.User;
import myy803.springboot.bookstore.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AuthController.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLogin() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("auth/signin"));
    }

    @Test
    public void testRegister() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("auth/signup"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    public void testRegisterUserAlreadyPresent() throws Exception {
        when(userService.isUserPresent(any(User.class))).thenReturn(true);

        mockMvc.perform(post("/save")
                        .param("username", "elena")
                        .param("password", "aa"))
                .andExpect(status().isOk())
                .andExpect(view().name("auth/signin"))
                .andExpect(model().attribute("successMessage", "User already registered!"));
    }

    @Test
    public void testRegisterUserSuccess() throws Exception {
        when(userService.isUserPresent(any(User.class))).thenReturn(false);

        mockMvc.perform(post("/save")
                        .param("username", "elena")
                        .param("password", "aa"))
                .andExpect(status().isOk())
                .andExpect(view().name("auth/signin"))
                .andExpect(model().attribute("successMessage", "User registered successfully!"));
    }
}
