package crud1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crud1.entity.Seller;
import crud1.exception.GlobalException;
import crud1.userService.SellerService;

@RestController
@RequestMapping("/seller")
@CrossOrigin(origins = "http://localhost:4200")
public class SellerLoginController {
	
	@Autowired
	SellerService service;
	
	@PostMapping("/register")
	public Seller registerSeller(@RequestBody Seller seller) {
		String tempEmail = seller.getEmail();
		Seller sellerObj = null;
		if(tempEmail != null && !"".equals(tempEmail)) {
			Seller emailExist = service.findSellerByEmail(tempEmail);
			if(emailExist != null) {
				try {
					throw new GlobalException("Email id "+ tempEmail +" Already Exist...");
				}catch(Exception e) {
//					String msg = 
//					return 
					System.out.println(e.getMessage());
				}
			}else {
				sellerObj = service.register(seller);
				System.out.println("Seller Registered Successfully..");
			}
		}
		return sellerObj;
	}
	
	@PostMapping("/login")
	public Seller login(@RequestBody Seller seller) {
		String tempEmail = seller.getEmail();
		String tempPassword = seller.getPassword();
		Seller emailExist = null;
		
		Seller loggedInSeller = null;
		
		
		if(tempEmail != null && tempPassword != null) {
			emailExist = service.findSellerByEmail(tempEmail);
			if(emailExist != null) {
				loggedInSeller = service.findByEmailandPassword(tempEmail, tempPassword);
				if(loggedInSeller == null) {
					try {
						throw new GlobalException("Seller Password is Incorrect");
					}catch(Exception e) {
						System.out.println(e.getMessage());
						return null;
					}
				}
				System.out.println("Login Successful");
			}else {
				throw new GlobalException("Email id does not exist");
			}
		}
		
		return loggedInSeller;
	}
	
	@GetMapping("/emailExist/{email}")
	public Seller emailIdExist(@PathVariable String email) {
		Seller emailExist = service.findSellerByEmail(email);
		if(emailExist != null) {
			System.out.println(emailExist);
		}else {
			try {				
				throw new GlobalException("Email id does not exist");
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return emailExist;
	}
	
	@GetMapping("/getAllSeller")
	public List<Seller> getAllSeller(){
		return service.showAllUser();
	}
	

}
