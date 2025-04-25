package myy803.springboot.bookstore.dao;

import myy803.springboot.bookstore.model.Request;
import myy803.springboot.bookstore.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RequestMapper extends JpaRepository<Request,Integer> {
    Optional<Request> findByUsername(String username);
}
