package crud1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import crud1.entity.Seller;

public interface SellerLoginRepo extends JpaRepository<Seller, Integer>{
	
	public Seller findByEmailAndPassword(String email ,String password);

	public Seller findByEmail(String email);
}
