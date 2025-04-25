package myy803.springboot.bookstore;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import myy803.springboot.bookstore.dao.BookMapper;
import myy803.springboot.bookstore.forms.UserProfileFormData;
import myy803.springboot.bookstore.service.UserProfileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import java.util.Collections;

@WebMvcTest(UserControllerTest.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserProfileService userProfileService;

    @Mock
    private UserDetails userDetails;

    @Mock
    private Model model;

    @Mock
    private BookMapper bookMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        when(userDetails.getUsername()).thenReturn("elena");

        Authentication auth = mock(Authentication.class);
        when(auth.getPrincipal()).thenReturn(userDetails);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    public void testRetrieveProfile() throws Exception {
        UserProfileFormData userProfileFormData = new UserProfileFormData();
        when(userProfileService.retrieveProfile(eq("elena"), any(UserProfileFormData.class)))
                .thenReturn(userProfileFormData);

        mockMvc.perform(get("/user/profile")
                        .with(user(userDetails)))
                .andExpect(status().isOk())
                .andExpect(view().name("user/profile"))
                .andExpect(model().attribute("userProfile", userProfileFormData));
    }

    @Test
    public void testShowForm() throws Exception {
        mockMvc.perform(get("/user/form")
                        .with(user(userDetails)))
                .andExpect(status().isOk())
                .andExpect(view().name("user/form"))
                .andExpect(model().attributeExists("userprofile"));
    }

    @Test
    @WithMockUser(username = "elena")
    public void testSaveProfile() throws Exception {
        mockMvc.perform(post("/saveProfile")
                        .param("fullName", "elena agg")
                        .param("address", "aloudi 45")
                        .param("age", "25")
                        .param("phone", "6985258659"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/form"))
                .andExpect(model().attribute("successMessage", "UserProfile made successfully!"));
    }

    @Test
    @WithMockUser(username = "elena")
    public void testListBookOffers() throws Exception {
        when(bookMapper.findByUsername("elena")).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/user/bookOffers"))
                .andExpect(status().isOk())
                .andExpect(view().name("/user/bookOffers"))
                .andExpect(model().attributeExists("bookss"));
    }

    @Test
    @WithMockUser(username = "elena")
    public void testShowOfferForm() throws Exception {
        mockMvc.perform(get("/user/bookform"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/bookform"))
                .andExpect(model().attributeExists("bookss"));
    }

    @Test
    @WithMockUser(username = "elena")
    public void testSaveOffer() throws Exception {
        mockMvc.perform(post("/saveOffer")
                        .param("bookTitle", "Test Book")
                        .param("Author", "Test Author")
                        .param("Category", "Fiction")
                        .param("Summary", "This is a test book"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/bookform"))
                .andExpect(model().attribute("successMessage", "Book was Added!"));
    }


}
