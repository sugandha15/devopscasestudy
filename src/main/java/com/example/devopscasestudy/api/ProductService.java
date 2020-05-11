package com.example.devopscasestudy.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.devopscasestudy.model.Product;
import com.example.devopscasestudy.repo.ProductRepository;

@Service
public class ProductService {

@Autowired
private ProductRepository productRepository;


public List<Product> findAll(){
return productRepository.findAll();
}


public List<Product> findByPriceGreaterThan(double price){
return productRepository.findByPriceGreaterThan(price);
}


public List<Product> findByProductName(String name){
return productRepository.findByProductNameIgnoreCase(name);
}


public void delete(int productId) {
productRepository.deleteById(productId);
}

public Product save(Product product) {
return productRepository.save(product);
}


}