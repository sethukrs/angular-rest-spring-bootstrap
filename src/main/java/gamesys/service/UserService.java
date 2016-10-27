package gamesys.service;

import java.util.Set;
import gamesys.model.User;

public interface UserService {
	
	User findById(int id);
	
	void register(User user);
	
	void deregisterUserById(int id);

	Set<User> findAllUsers();
	
	void deregisterAllUsers();
	
	boolean isUserRegistered(User user);
	
}
