package crud1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import crud1.userService.ProductServiceImpl;

//import com.img.entity.Product;

import crud1.entity.User;
import crud1.exception.GlobalException;
import crud1.entity.Product;
import crud1.entity.Seller;
import crud1.userService.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	@Autowired UserService userService;
	@Autowired ProductServiceImpl service;
	
	@PostMapping("/register")
	public User registerUser(@RequestBody User user) {
		String tempEmail = user.getEmail();
		User userObj = null;
		if(tempEmail != null && !"".equals(tempEmail)) {
			User emailExist = userService.findUserByEmail(tempEmail);
			if(emailExist != null) {
				try {
					throw new GlobalException("Email id "+ tempEmail +" Already Exist...");
				}catch(Exception e) {
//					String msg = 
//					return 
					System.out.println(e.getMessage());
				}
			}else {
				userObj = userService.addUser(user);
				System.out.println("User Registered Successfully..");
			}
		}
		return userObj;
	}
	
	
	@GetMapping("/getAllUser")
	public List<User> getAllUsers(){
		return userService.showAllUser();
	}
	
	@PostMapping("/login")
	public User findbyEmailAndPassword(@RequestBody User user) throws Exception {
		String tempEmail = user.getEmail();
		String tempPassword = user.getPassword();
		User loginUser = null;
		User emailExist = null;
		
		if(tempEmail != null && tempPassword != null) {
			emailExist = userService.findUserByEmail(tempEmail);
			if(emailExist != null) {
				loginUser = userService.findbyEmailAndPassword(tempEmail, tempPassword);
				if(loginUser == null) {
					try {
						throw new GlobalException("User Password is Incorrect");
					}catch(Exception e) {
						System.out.println(e.getMessage());
						return null;
					}
				}
				System.out.println("Welcome "+ loginUser.getFirstName());
			}else {
				try {
					throw new GlobalException("Email id is Incorrect");
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
		
		return loginUser;
	}
	
	@GetMapping("/emailExist/{email}")
	public User emailAlreadyExist(@PathVariable String email) {
		User emailCheck = userService.findUserByEmail(email);
		if(emailCheck != null) {
			System.out.println(emailCheck);
//			return emailCheck;
		}else {
			try {				
				throw new GlobalException("Email id does not exist");
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return emailCheck;
		
	}
	
	@PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            // Create an Image entity and save it
            Product image = new Product();
            image.setProductName(file.getOriginalFilename());
            // Set other properties if available

            // Save the image
            Product savedImage = service.saveImage(image);

            // You can return the saved image details or just a success message
            return ResponseEntity.ok("Image uploaded successfully with ID: " + savedImage.getId());
        } catch (Exception e) {
        	e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload image: " + e.getMessage());
        }
    }
	
	@GetMapping("/getAllProducts")
	public List<Product> getAllProduct(){
		return service.getAllProduct();
	}
}
