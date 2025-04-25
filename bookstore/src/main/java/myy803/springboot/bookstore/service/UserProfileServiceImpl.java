package myy803.springboot.bookstore.service;

import java.util.List;
import java.util.Optional;


import myy803.springboot.bookstore.dao.BookMapper;
import myy803.springboot.bookstore.dao.UserProfileMapper;
import myy803.springboot.bookstore.forms.UserProfileFormData;
import myy803.springboot.bookstore.model.Book;
import myy803.springboot.bookstore.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserProfileServiceImpl implements UserProfileService {
	
	
	
	 @Autowired
	 private UserProfileMapper userProfileMapper;


	 @Autowired
	 private BookMapper bookMapper;


	 /*@Autowired
	 private SearchFactory searchFactory; 

	 @Autowired
	 private RecommendationsFactory  recommendationsFactory; */
	 
	 

	
	
	
	
	public UserProfileFormData retrieveProfile(String username, UserProfileFormData userProfileFormData) {

		// Call userProfileMapper.findByUsername to retrieve the UserProfile
		Optional<UserProfile> userProfileOptional = userProfileMapper.findByUsername(username);

		if (userProfileOptional.isPresent()) {
			UserProfile userProfile = userProfileOptional.get();
			userProfileFormData.setFullName(userProfile.getFullName());
			userProfileFormData.setAddress(userProfile.getAddress());
			userProfileFormData.setAge(userProfile.getAge());
			userProfileFormData.setPhoneNumber(userProfile.getPhoneNumber());
			userProfileFormData.setFavouriteBookCategories(userProfile.getFavouriteBookCategories());
			userProfileFormData.setFavouriteBookAuthors(userProfile.getFavouriteBookAuthors());

			return userProfileFormData;
		} else {
			return null;
		}



}

	private UserProfile updateProfileData(UserProfile profile, UserProfileFormData formData) {
		// Update the profile data with the form data
		profile.setFullName(formData.getFullName());
		profile.setAddress(formData.getAddress());
		profile.setAge(formData.getAge());
		profile.setPhoneNumber(formData.getPhoneNumber());
		profile.setFavouriteBookCategories(formData.getFavouriteBookCategories());
		profile.setFavouriteBookAuthors(formData.getFavouriteBookAuthors());

		return profile;
	}


	public void save(UserProfileFormData userProfileFormData , UserProfile userProfile) {
		Optional<UserProfile> userProfileOptional = userProfileMapper.findByUsername(userProfile.getUsername());
		if (userProfileOptional.isPresent()) {

			// If a profile exists, update it with the new data
			UserProfile existingProfile = userProfileOptional.get();
			updateProfileData(existingProfile, userProfileFormData);
			userProfileMapper.save(updateProfileData(existingProfile, userProfileFormData));
		} else {
			userProfile.setFullName(userProfileFormData.getFullName());
			userProfile.setAddress(userProfileFormData.getAddress());
			userProfile.setAge(userProfileFormData.getAge());
			userProfile.setPhoneNumber(userProfileFormData.getPhoneNumber());
			userProfile.setFavouriteBookCategories(userProfileFormData.getFavouriteBookCategories());
			userProfile.setFavouriteBookAuthors(userProfileFormData.getFavouriteBookAuthors());

			userProfileMapper.save(userProfile);

		}

	}

	@Override
	public List<Book> findAll(){

		return bookMapper.findAll();
		
	}

	@Override
	public void addBookOffer(Book book) {
		bookMapper.save(book);
	}

	/*public List <BookFormData> searchBooks (SearchFormData searchFormData){
		return null;
		
	}

	public List<BookFormData> recommendBooks(String username, RecommendationsFormData recomFormData){
		return null;}
	public List<BookFormData> retrieveBookRequests(String username){
		return null;
		
	}
	
	public List<UserProfileFormData> retrieveRequestingUsers(int bookid){
		return null;
		
	}*/

	public void deleteBookOffer (int bookid) {
		bookMapper.deleteById((long) bookid);
	}

	/*public void deleteBookRequest(String username, int bookld) {
		
	}*/


}



