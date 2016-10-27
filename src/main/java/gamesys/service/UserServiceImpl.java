package gamesys.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import gamesys.model.User;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	private static final AtomicInteger counter = new AtomicInteger();
	
	private static Set<User> users = new HashSet();
	
	static{
		users= populateDummyUsers();
	}

	public Set<User> findAllUsers() {
		return users;
	}
	
	public User findById(int id) {
		for(User user : users){
			if(user.getId() == id){
				return user;
			}
		}
		return null;
	}

	public void register(User user) {
		user.setId(counter.incrementAndGet());
		users.add(user);
	}

	public void deregisterUserById(int id) {
		for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
		    User user = iterator.next();
		    if (user.getId() == id) {
		        iterator.remove();
		    }
		}
	}

	public boolean isUserRegistered(User user) {
		return users.contains(user);
	}
	
	public void deregisterAllUsers(){
		users.clear();
	}

	private static Set<User> populateDummyUsers(){
		Set<User> users = new HashSet();
		users.add(new User(counter.incrementAndGet(),"Sam1", "aaA1", "1985-10-17T23:00:00.000Z", "123-11-1234"));
		users.add(new User(counter.incrementAndGet(),"Tomy1", "bbB1", "1998-11-17T23:00:00.000Z", "123-11-1233"));
		users.add(new User(counter.incrementAndGet(),"Kelly1", "ccC1", "1996-06-11T23:00:00.000Z", "123-11-1232"));
		return users;
	}

}
