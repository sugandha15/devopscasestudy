package com.example.devopscasestudy.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.devopscasestudy.repo.ProductRepository;

@RestController
public class ProductAPI {

private final Logger logger = LoggerFactory.getLogger(getClass());

// @Autowired
// private ProductRepository productRepository;
//


@Autowired
private ProductFacade productFacade;



@GetMapping("/products")
public ResponseEntity<List<ProductDTO>> findAll(){
logger.info("Processing findAll request");


return new ResponseEntity<>(productFacade.findAll(), HttpStatus.OK);
}

//http://localhost:8081/products/5674
@GetMapping("/products/{price}")
public ResponseEntity<List<ProductDTO>> findByPrice(@PathVariable("price")double price){

return new ResponseEntity<>(productFacade.findByPriceGreaterThan(price), HttpStatus.OK);
}

@GetMapping("/products/find/{name}")
public ResponseEntity<List<ProductDTO>> findByName(@PathVariable("name")String name){

logger.info("Processing findByName request");

return new ResponseEntity<>(productFacade.findByProductName(name), HttpStatus.OK);
}

// "\"This is a String\""

//http://localhost:8081/products/5674
@DeleteMapping("/products/{productId}")

public @ResponseBody ResponseEntity<StringResponse> delete(@PathVariable("productId")int productId){
productFacade.delete(productId);





return new ResponseEntity<>(new StringResponse("Deleted Order "+productId), HttpStatus.OK);

}


// app.post('/products',(req,res)=>)
@PostMapping("/products")
public ResponseEntity<ProductDTO> save(@RequestBody ProductDTO productDTO){


productFacade.save(productDTO);

return ResponseEntity.status(HttpStatus.CREATED).body(productDTO);
}






}