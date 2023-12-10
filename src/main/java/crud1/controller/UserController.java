package crud1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import crud1.entity.User;
import crud1.userService.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	@Autowired UserService userService;
	
	@PostMapping("/")
	public User addUser(@RequestBody User user) {
		return userService.addUser(user);
	}
	
	
	@GetMapping("/")
	public List<User> getAllUsers(){
		return userService.showAllUser();
	}
	
	@PostMapping("/login")
	public User findbyEmailAndPassword(@RequestBody User user) throws Exception {
		String tempEmail;
		String tempPassword;
		
		tempEmail = user.getEmail();
		tempPassword = user.getPassword();
		
		User loginUser = null;
		
		if(tempEmail != null && tempPassword != null) {
			loginUser = userService.findbyEmailAndPassword(user.getEmail(), user.getPassword());
		}else {
			throw new Exception("Please provide valid Credientials");
		}
		
		System.out.println("Welcome "+ loginUser.getFirstName());
		return loginUser;
		
		
		
	}

}
