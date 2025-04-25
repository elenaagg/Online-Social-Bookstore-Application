package myy803.springboot.bookstore.dao;

import myy803.springboot.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookMapper extends JpaRepository<Book, Long> {

        List<Book> findByUsername(String username);
        //List<Book> findByTitle(String bookTitle);
        List<Book> findByBookTitleAndAuthors(String bookTitle,String Authors);
        //List<Book> findByTitleContaining(String bookTitle);
        List<Book> findByAuthors(String Authors);
        List<Book> findByCategoryOrAuthors(String Category,String Authors);



}

