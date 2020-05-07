package com.example.devopscasestudy.repo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.devopscasestudy.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer>{
	
	List<Product> deleteById(int productId);
	List<Product> findById(int productId);
	List<Product> findByPriceGreaterThan(double price);
	
	List<Product> findByProductNameIgnoreCase(String productName);
	// ingore case ignores the case sensitive name upper case or lower case
}