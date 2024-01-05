package crud1.userService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import crud1.entity.Product;
import crud1.repository.ProductRepository;

@Service

public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductRepository repo;
	
	public Product saveImage(Product image) {
        return repo.save(image);
    }
	
	public List<Product> getAllProduct(){
		List<Product> products = new ArrayList<Product>();
	      repo.findAll().forEach(product -> products.add(product));
	      return products; 
	}
	

	    public String saveImage(MultipartFile file) throws IOException {
	        // Logic to save the image, for example, to a directory
	        String imagePath = "/path/to/save/images/" + file.getOriginalFilename();
	        Path filePath = Paths.get(imagePath);
	        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
	        return imagePath; // Return the path where the image is saved
	    }
	    
		@Override
		public Product saveProduct(Product product) {
			return repo.save(product);
		}
		
		


}
