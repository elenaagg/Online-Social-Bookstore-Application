package myy803.springboot.bookstore.service;

import myy803.springboot.bookstore.forms.UserProfileFormData;
import myy803.springboot.bookstore.model.Book;
import myy803.springboot.bookstore.model.UserProfile;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserProfileService {

	public UserProfileFormData retrieveProfile(String username,UserProfileFormData userProfileFormData);

	public void save(UserProfileFormData userProfileFormData, UserProfile userProfile);

	public List<Book> findAll();

	public void addBookOffer(Book book);

	/*public List <BookFormData> searchBooks (SearchFormData searchFormData);

	public List<BookFormData> recommendBooks(String username, RecommendationsFormData recomFormData);

	public void requestBook(int bookld, String username);

	public List<UserProfileFormData> retrieveRequestingUsers(int bookid);*/

	public void deleteBookOffer(int bookid);

	/*public void deleteBookRequest(String username, int bookld);*/

}
