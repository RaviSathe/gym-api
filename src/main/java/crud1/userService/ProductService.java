package crud1.userService;

import java.io.IOException;
import java.util.List;

import crud1.entity.Product;

import org.springframework.web.multipart.MultipartFile;

public interface ProductService {
	
	public Product saveImage(Product file);
	
	public Product saveProduct(Product product);
	
	public List<Product> getAllProduct();

}
