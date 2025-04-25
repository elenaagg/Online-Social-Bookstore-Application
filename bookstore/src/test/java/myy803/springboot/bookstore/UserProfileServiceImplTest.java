package myy803.springboot.bookstore;

import myy803.springboot.bookstore.dao.BookMapper;
import myy803.springboot.bookstore.dao.UserProfileMapper;
import myy803.springboot.bookstore.forms.UserProfileFormData;
import myy803.springboot.bookstore.model.Book;
import myy803.springboot.bookstore.model.UserProfile;
import myy803.springboot.bookstore.service.UserProfileServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UserProfileServiceImplTest {

    @Mock
    private UserProfileMapper userProfileMapper;

    @InjectMocks
    private UserProfileServiceImpl userProfileService;

    @Mock
    private BookMapper bookMapper;



    @Test
    void testSave_ProfileExists() {
        // Mock UserProfile and UserProfileFormData
        UserProfile userProfile = new UserProfile();
        UserProfileFormData formData = new UserProfileFormData();
        formData.setFullName("elena agg");
        formData.setAddress("kaloudi 45");
        formData.setAge(30);
        formData.setPhoneNumber("6952685985");
        formData.setFavouriteBookCategories("Fantasy");
        formData.setFavouriteBookAuthors("J.K. Rowling");

        // Mock findByUsername method of UserProfileMapper to return an existing UserProfile
        Mockito.when(userProfileMapper.findByUsername(Mockito.anyString())).thenReturn(Optional.of(userProfile));

        // Call the save method
        userProfileService.save(formData, userProfile);

        // Verify that the UserProfile is updated with the new data and saved
        Mockito.verify(userProfileMapper).save(userProfile);
    }

    @Test
    void testSave_ProfileDoesNotExist() {
        // Mock UserProfile and UserProfileFormData
        UserProfile userProfile = new UserProfile();
        UserProfileFormData formData = new UserProfileFormData();
        formData.setFullName("elena agg");
        formData.setAddress("kaloudi 45");
        formData.setAge(30);
        formData.setPhoneNumber("6952685985");
        formData.setFavouriteBookCategories("Fantasy");
        formData.setFavouriteBookAuthors("J.K. Rowling");

        // Mock findByUsername method of UserProfileMapper to return an empty Optional
        Mockito.when(userProfileMapper.findByUsername(Mockito.anyString())).thenReturn(Optional.empty());

        // Call the save method
        userProfileService.save(formData, userProfile);

        // Verify that the UserProfile is created with the new data and saved
        Mockito.verify(userProfileMapper).save(userProfile);
    }

    @Test
    void testFindAll() {
        // Mock the behavior of findAll method of BookMapper
        List<Book> books = new ArrayList<>();
        books.add(new Book());
        Mockito.when(bookMapper.findAll()).thenReturn(books);

        // Call the findAll method of UserProfileServiceImpl
        List<Book> result = userProfileService.findAll();

        // Verify that the result is not null and contains the expected number of books
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testAddBookOffer() {
        // Mock the behavior of save method of BookMapper
        Book book = new Book();
        Mockito.when(bookMapper.save(book)).thenReturn(book);

        // Call the addBookOffer method of UserProfileServiceImpl
        userProfileService.addBookOffer(book);

        // Verify that the save method of BookMapper was called with the correct argument
        Mockito.verify(bookMapper).save(book);
    }

    @Test
    void testDeleteBookOffer() {
        // Mock the behavior of deleteById method of BookMapper
        Mockito.doNothing().when(bookMapper).deleteById(Mockito.anyLong());

        // Call the deleteBookOffer method of UserProfileServiceImpl
        userProfileService.deleteBookOffer(123);

        // Verify that the deleteById method of BookMapper was called with the correct argument
        Mockito.verify(bookMapper).deleteById(123L);
    }

}

