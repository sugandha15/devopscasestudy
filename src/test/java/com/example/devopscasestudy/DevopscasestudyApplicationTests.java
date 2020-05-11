package com.example.devopscasestudy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.devopscasestudy.api.ProductAPI;
import com.example.devopscasestudy.api.ProductDTO;
import com.example.devopscasestudy.api.ProductFacade;
import com.example.devopscasestudy.api.ProductService;
import com.example.devopscasestudy.model.Product;
import com.example.devopscasestudy.repo.ProductRepository;

@SpringBootTest
public class DevopscasestudyApplicationTests {



@InjectMocks
ProductAPI productapi;

@Mock
ProductFacade productFacade;






@Test
void contextLoads() {
}

@Test
public void testFindAll()
{
// given


List<ProductDTO> products = new ArrayList<>();
products.add(new ProductDTO());
products.add(new ProductDTO());
//products.add(new Product());

when(productFacade.findAll()).thenReturn(products);



// when
List<ProductDTO> result = productapi.findAll().getBody();

// then
assertThat(result.size()).isEqualTo(2);

// assertThat(result.get(0).getFirstName())
// .isEqualTo(employee1.getFirstName());
//
// assertThat(result.getEmployeeList().get(1).getFirstName())
// .isEqualTo(employee2.getFirstName());
}

}