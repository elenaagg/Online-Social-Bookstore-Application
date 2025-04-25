package myy803.springboot.bookstore.controller;

import myy803.springboot.bookstore.dao.BookMapper;
import myy803.springboot.bookstore.dao.UserDAO;
import myy803.springboot.bookstore.forms.BookFormData;
import myy803.springboot.bookstore.forms.UserProfileFormData;
import myy803.springboot.bookstore.model.Book;
import myy803.springboot.bookstore.model.UserProfile;
import myy803.springboot.bookstore.service.UserProfileService;
import myy803.springboot.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    UserDAO userDAO;


    @Autowired
    UserProfileService userProfileService;


    @RequestMapping("/user/dashboard")
    public String getUserHome(){
        return "user/dashboard";
    }



    @RequestMapping("user/profile")
    public String retrieveProfile(Model model) {
        // Retrieve the username of the authenticated user
        String username = getUsername();
        // Retrieve the user profile for the authenticated user
        UserProfileFormData ProfileFormData = new UserProfileFormData();
        UserProfileFormData userProfile = userProfileService.retrieveProfile(username,ProfileFormData);


        // Add the retrieved user profile data to the model for rendering in the view
        model.addAttribute("userProfile", userProfile);
        return "user/profile";
    }

    private String getUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }

    @RequestMapping("/user/form")
    public String showForm(Model model) {
        UserProfileFormData userProfileFormData = new UserProfileFormData();
        // Populate any necessary default values or retrieve existing data here
        model.addAttribute("userprofile", userProfileFormData);
        return "user/form";

    }



    @RequestMapping("/saveProfile")
    public String saveProfile(@ModelAttribute("userprofile") UserProfileFormData userProfileFormData, Model theModel) {

        // Retrieve the username of the currently logged-in user
        String loggedInUsername = getUsername();

        UserProfile userProfile = new UserProfile();
        userProfile.setUsername(loggedInUsername);



        // save the userprofile
        userProfileService.save(userProfileFormData,userProfile);
        theModel.addAttribute("successMessage", "UserProfile made successfully!");

        // redirect to /user/profile ACTION
        return "redirect:/user/profile";

    }
    @RequestMapping("/user/bookOffers")
    public String listBookOffers(Model theModel) {
        String loggedInUsername = getUsername();
        List<Book> Books = bookMapper.findByUsername(loggedInUsername);
        theModel.addAttribute("bookss", Books);
        return "/user/bookOffers";

    }

    @RequestMapping("/user/bookform")
    public String showOfferForm(Model theModel) {
        BookFormData books = new BookFormData();
        theModel.addAttribute("bookss", books);
        return "user/bookform";

    }

    @RequestMapping("/saveOffer")
    public String saveOffer(@ModelAttribute("bookss") Book book, Model theModel) {
        String loggedInUsername = getUsername();
        book.setUsername(loggedInUsername);
        userProfileService.addBookOffer(book);
        theModel.addAttribute("successMessage", "Book was Added!");
        return "redirect:/user/bookOffers";

    }


    /*public String showSearchForm(Model model) {
        return null;

    }


    public String search(SearchFormData searchFormData, Model model) {
        return null;

    }


    public String showRecommendationsForm(Model model) {
        return null;

    }


    public String requestBook(int bookid, Model model) {
        return null;

    }

    public String showUserBookRequests(Model model) {
        return null;

    }

    public String showRequestingUsersForBookOffer(int bookid, Model model) {
        return null;

    }

    public String acceptRequest(String username, int bookid, Model model) {
        return username;

    }*/

    @RequestMapping("/deleteBookOffer")
    public String deleteBookOffer(@RequestParam("BookId") int bookid) {
        userProfileService.deleteBookOffer(bookid);
        return "redirect:user/bookOffers";

    }


    /*public String deleteBookRequest(String username, int bookid, Model model) {
        return username;

    }*/

}
