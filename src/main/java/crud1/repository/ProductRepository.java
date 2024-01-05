package crud1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import crud1.entity.Product;


public interface ProductRepository extends JpaRepository<Product, Integer>{

}
