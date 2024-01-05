package crud1.controller;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import crud1.entity.Product;
import crud1.exception.GlobalException;
import crud1.userService.ProductServiceImpl;
import jakarta.annotation.Resource;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/product")
//@RequestMapping("/api/images")
public class ProductController {
	
	@Autowired ProductServiceImpl service;
	
	@PostMapping("/api/images/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            // Save the image using a service
            String imagePath = service.saveImage(file);
            return ResponseEntity.ok(imagePath); // Return image path or success message
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload image: " + e.getMessage());
        }
    }
	
	@GetMapping("/api/images/get/{imagePath:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String imagePath) {
        // Load the image as a resource and return it
        Resource resource = (Resource) new FileSystemResource(imagePath);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // Adjust content type based on your image type
                .body(resource);
    }
	
	
	@GetMapping("/getAllProducts")
	public List<Product> getAllProduct(){
		return service.getAllProduct();
	}
	
	@PostMapping("/addProduct")
	public Product addProduct(@RequestBody Product product) {
		Product pro = service.saveProduct(product);
		if(pro != null) {
			System.out.println(pro);
		}else {
			throw new GlobalException("Please Enter proper product details");
		}
		return pro;
	}

	@GetMapping("/generatedId")
	public int generateIfForSeller() {
		Random random = new Random();
		int randomNumber = random.nextInt(100000000, 1000000000);
		return randomNumber;
	}

}
