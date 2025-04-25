package myy803.springboot.bookstore.service;

import org.springframework.stereotype.Service;

import myy803.springboot.bookstore.model.User;

@Service
public interface UserService {
	public void saveUser(User user);
    public boolean isUserPresent(User user);
}
