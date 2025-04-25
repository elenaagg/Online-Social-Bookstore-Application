package myy803.springboot.bookstore.model;

import jakarta.persistence.*;


@Entity
@Table(name="usersprofile")
public class UserProfile{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="user_name", unique=true)
    private String username;

    @Column(name="full_name")
    private String fullName;

    @Column(name="address")
    private String address;

    @Column(name="age")
    private int age;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="favouriteBookCategories")
    private String favouriteBookCategories;


    @Column(name="favouriteBookAuthors")
    private String favouriteBookAuthors;

    @Enumerated(EnumType.STRING)
    @Column(name="role")
    private Role role;




    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public int getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRole(Role role) {
        this.role = role;
    }



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

    public String getFavouriteBookAuthors() {
        return favouriteBookAuthors;
    }

    public void setFavouriteBookAuthors(String favouriteBookAuthors) {
        this.favouriteBookAuthors = favouriteBookAuthors;
    }



    /*public void setBookOffers(List<Book> bookOffers) {
        this.bookOffers = bookOffers;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }*/
}
