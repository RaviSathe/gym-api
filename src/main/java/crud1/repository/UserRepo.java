package crud1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import crud1.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}