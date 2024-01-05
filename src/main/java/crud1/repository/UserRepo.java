package crud1.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import crud1.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	
	public User findByEmailAndPassword(String email ,String password);
	
	public User findByEmail(String email);

}
