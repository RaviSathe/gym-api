package crud1.userService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crud1.entity.User;
import crud1.repository.UserRepo;

@Service
public class UserService{
	
	@Autowired private UserRepo repo;
	
	public User addUser(User user) {
		return repo.save(user);
	}
	
	public User updateUser(User user) {
		return repo.save(user);
	}
	
	public List<User> showAllUser(){
		 List<User> users = new ArrayList<User>();
	      repo.findAll().forEach(user -> users.add(user));
	      return users; 
		
	}

}
