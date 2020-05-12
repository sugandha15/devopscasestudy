package com.example.devopscasestudy.api;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.devopscasestudy.model.Product;

@Component
public class ProductFacade {


@Autowired
private ProductService productService;

@Autowired
private ModelMapper modelMapper;

public List<ProductDTO> findAll(){
return convertToProductDto(productService.findAll());
}


public List<ProductDTO> findByPriceGreaterThan(double price){
return convertToProductDto( productService.findByPriceGreaterThan(price));
}


public List<ProductDTO> findByProductName(String name){
return convertToProductDto(productService.findByProductName(name));
}


public void delete(int productId) {
productService.delete(productId);
}

public void save(ProductDTO productDTO) {
productService.save(convertToEntity(productDTO));
}

private List<ProductDTO> convertToProductDto(List<Product> products) {
return modelMapper.map(products,new TypeToken<List<ProductDTO>>(){}.getType());

}

private Product convertToEntity(ProductDTO productDTO) {
return modelMapper.map(productDTO,new TypeToken<Product>(){}.getType());

}
}