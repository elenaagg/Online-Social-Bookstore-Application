package myy803.springboot.bookstore.model;


import jakarta.persistence.*;

@Entity
@Table(name="bookss")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="bookid")
	private int bookid;

	@Column(name="user_name")
	private String username;
	@Column(name="bookTitle")
	private String bookTitle;
	@Column(name="Authors")
	private String Authors;
	@Column(name="Category")
	private String Category;

	@Column(name="Summary")
	private String Summary;



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Book() {}



	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getAuthors() {
		return Authors;
	}

	public void setAuthors(String Authors) {
		this.Authors = Authors;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String Category) {
		this.Category = Category;
	}

	public String getSummary() {
		return Summary;
	}

	public void setSummary(String Summary) {
		this.Summary = Summary;
	}

	public int getBookid() {
		return bookid;
	}

	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
}

