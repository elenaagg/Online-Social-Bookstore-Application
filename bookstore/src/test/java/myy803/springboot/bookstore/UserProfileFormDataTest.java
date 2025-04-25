package myy803.springboot.bookstore;

import myy803.springboot.bookstore.forms.UserProfileFormData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(UserProfileFormDataTest.class)
public class UserProfileFormDataTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testUserProfile() throws Exception {
        // Setup
        UserProfileFormData expectedUserProfile = new UserProfileFormData();
        expectedUserProfile.setFullName("elena agg");
        expectedUserProfile.setAddress("kaloudi 45");
        expectedUserProfile.setAge(30);
        expectedUserProfile.setPhoneNumber("6956845268");
        expectedUserProfile.setFavouriteBookCategories("Fantasy");
        expectedUserProfile.setFavouriteBookAuthors("J.K. Rowling");

        // Perform and Verify
        mockMvc.perform(MockMvcRequestBuilders.get("/profile"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("userProfileFormData"))
                .andExpect(MockMvcResultMatchers.model().attribute("userProfileFormData", expectedUserProfile))
                .andExpect(MockMvcResultMatchers.view().name("profile"));
    }
}
