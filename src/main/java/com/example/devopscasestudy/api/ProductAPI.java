package com.example.devopscasestudy.api;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.devopscasestudy.model.Product;
import com.example.devopscasestudy.repo.ProductRepository;

@RestController
public class ProductAPI {

private final Logger logger = LoggerFactory.getLogger(getClass());

@Autowired
private ProductRepository productRepository;

@Autowired
private ModelMapper modelMapper;


// node express js
// app.get('/products',(req,res)=>)

@GetMapping("/products")
public ResponseEntity<List<ProductDTO>> findAll(){
logger.info("Processing findAll request");
List<Product> products=productRepository.findAll();


return new ResponseEntity<>(products.stream()
.map(this::convertToDto)
.collect(Collectors.toList()), HttpStatus.OK);
}

//http://localhost:8081/products/5674
@GetMapping("/products/{price}")
public ResponseEntity<List<ProductDTO>> findByPrice(@PathVariable("price")double price){
List<Product> products=productRepository.findByPriceGreaterThan(price);
return new ResponseEntity<>(products.stream()
.map(this::convertToDto)
.collect(Collectors.toList()), HttpStatus.OK);
}

@GetMapping("/products/find/{name}")
public ResponseEntity<List<ProductDTO>> findByName(@PathVariable("name")String name){

logger.info("Processing findByName request");
List<Product> products=productRepository.findByProductNameIgnoreCase(name);
return new ResponseEntity<>(products.stream()
.map(this::convertToDto)
.collect(Collectors.toList()), HttpStatus.OK);
}

// "\"This is a String\""

//http://localhost:8081/products/5674
@DeleteMapping("/products/{productId}")

public @ResponseBody ResponseEntity<StringResponse> delete(@PathVariable("productId")int productId){
productRepository.deleteById(productId);





return new ResponseEntity<>(new StringResponse("Deleted Order "+productId), HttpStatus.OK);

}


// app.post('/products',(req,res)=>)
@PostMapping("/products")
public ResponseEntity<ProductDTO> save(@RequestBody ProductDTO productDTO){
Product product=this.convertToEntity(productDTO);

productRepository.save(product);
return new ResponseEntity<>(productDTO, HttpStatus.CREATED);
}


private ProductDTO convertToDto(Product product) {
ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);

return productDTO;
}

private Product convertToEntity(ProductDTO productDTO) throws ParseException {
Product product = modelMapper.map(productDTO, Product.class);

if (productDTO.getProductId() != null) {
Product oldProduct = productRepository.getOne(productDTO.getProductId());

}
return product;
}



}