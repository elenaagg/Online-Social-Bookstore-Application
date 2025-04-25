package myy803.springboot.bookstore.forms;

public class UserProfileFormData {
    private String fullName;
    private String address;
    private int age;
    private String phoneNumber;
    private String favouriteBookCategories;
    private String favouriteBookAuthors;

    
    // Getters and setters
    public String getFullName() {

        return fullName;
    }

    public void setFullName(String fullName) {

        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {

        return age;
    }

    public void setAge(int age) {

        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFavouriteBookCategories() {
        return favouriteBookCategories;
    }

    public void setFavouriteBookCategories(String favouriteBookCategories) {
        this.favouriteBookCategories = favouriteBookCategories;
    }


    public void setFavouriteBookAuthors(String favouriteBookAuthors) {
        this.favouriteBookAuthors=favouriteBookAuthors;
    }

    public String getFavouriteBookAuthors() {
        return favouriteBookAuthors;
    }
}
