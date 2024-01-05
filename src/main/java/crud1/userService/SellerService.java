package crud1.userService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crud1.entity.Seller;
import crud1.repository.SellerLoginRepo;

@Service
public class SellerService {
	
	@Autowired
	SellerLoginRepo sellerRepo;
	
	public Seller register(Seller seller) {
		return sellerRepo.save(seller);
	}
	
	public Seller login(Seller seller) {
		return sellerRepo.findByEmailAndPassword(seller.getEmail() , seller.getPassword());
	}
	
	public Seller findSellerByEmail(String email) {
		return sellerRepo.findByEmail(email);
	}
	
	public Seller findByEmailandPassword(String email , String password) {
		return sellerRepo.findByEmailAndPassword(email, password);
	}
	
	public List<Seller> showAllUser(){
		 List<Seller> seller = new ArrayList<Seller>();
		 sellerRepo.findAll().forEach(user -> seller.add(user));
	      return seller; 
	}

}
