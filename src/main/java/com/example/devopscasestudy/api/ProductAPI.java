package com.example.devopscasestudy.api;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.devopscasestudy.model.Product;
import com.example.devopscasestudy.repo.ProductRepository;

@RestController            //to expose us rest api
public class ProductAPI {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired             //look for productrepository obj and inject it here
	private ProductRepository productRepository;

	//node express js
	//app.get('/products',(req,res)=>)	
	@GetMapping("/products")
	
	public ResponseEntity<List<Product>> findAll(){
		logger.info("Processing findAll request");
		List<Product> products=productRepository.findAll();
		return new ResponseEntity<>(products,HttpStatus.OK);
	}
	
	@GetMapping("/products/{price}")
	public ResponseEntity<List<Product>> findByPrice(@PathVariable("price")double price){
		List<Product> products=productRepository.findByPriceGreaterThan(price);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
	
	//app.post('/products',(req,res)=>)	
	@PostMapping("/products")
	public ResponseEntity<Product> save(@RequestBody Product product){
		productRepository.save(product);
		return new ResponseEntity<>(product, HttpStatus.CREATED);
	}
	@PostMapping("/products/bulk")
    public ResponseEntity<List<Product>> saveAll( @RequestBody List <Product> product){
		
        productRepository.saveAll(product);
        
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }
	
	 
	 @GetMapping("/products/find/{name}")
		public ResponseEntity<List<Product>> findByName(@PathVariable("name")String name){
			List<Product> products=productRepository.findByProductNameIgnoreCase(name);
			return new ResponseEntity<>(products, HttpStatus.OK);
		}
		
	 
	 
	
	 @DeleteMapping("/products/{productId}")
     
     public @ResponseBody ResponseEntity<StringResponse> delete(@PathVariable("productId")int productId){
         productRepository.deleteById(productId);
         
  
        
     
         return new ResponseEntity<>(new StringResponse("Deleted Order "+productId), HttpStatus.OK);
    
     }
	
}