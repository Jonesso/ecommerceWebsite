package ru.pavlinina.ecommerce.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.pavlinina.ecommerce.models.Category;
import ru.pavlinina.ecommerce.models.Product;
import ru.pavlinina.ecommerce.repositories.ProductRepository;

/**
 * @author Sofia Pavlinina
 */
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ProductServiceTest {

  @Autowired
  private ProductService productService;

  @MockBean
  private ProductRepository productRepository;

  @Test
  void addProduct() {
    Product product = new Product();
    product.setProductId(1440);
    product.setProductName("some product");
    product.setProductPrice(123);
    product.setProductUnit(10);

    productService.addProduct(product);

    assertEquals(10, product.getProductUnit());
    assertEquals(123, product.getProductPrice());

    Mockito.doReturn(Optional.of(product))
        .when(productRepository)
        .findById(1440L);
    assertEquals(Optional.of(product), productService.getProductById(1440L));
  }

  @Test
  void getProductById() {
    Product product = new Product();
    product.setProductId(22L);
    product.setProductName("some product");
    product.setProductPrice(121);
    product.setProductUnit(1);

    productService.addProduct(product);

    Mockito.doReturn(Optional.of(product))
        .when(productRepository)
        .findById(22L);
    assertEquals(Optional.of(product), productService.getProductById(22L));
  }

  @Test
  void getAllProducts() {
    List<Product> productsList = new ArrayList<>();

    Product product1 = new Product();
    product1.setProductId(22L);
    product1.setProductName("some product");
    product1.setProductPrice(121);
    product1.setProductUnit(1);

    Product product2 = new Product();
    product2.setProductId(22L);
    product2.setProductName("some product");
    product2.setProductPrice(121);
    product2.setProductUnit(1);

    productService.addProduct(product1);
    productService.addProduct(product2);

    productsList.add(product1);
    productsList.add(product2);

    Mockito.doReturn(productsList)
        .when(productRepository)
        .findAll();
    assertEquals(productsList, productService.listProduct());
  }

  @Test
  void getProductsByCategoryId() {
    List<Product> productsList = new ArrayList<>();

    Category category = new Category();
    category.setCategoryId(600L);
    category.setCategoryName("Test");

    Product product1 = new Product();
    product1.setProductId(11L);
    product1.setProductName("some product");
    product1.setCategory(category);
    product1.setProductPrice(121);
    product1.setProductUnit(3);

    Product product2 = new Product();
    product2.setProductId(22L);
    product2.setProductName("some other product");
    product1.setCategory(category);
    product2.setProductPrice(121);
    product2.setProductUnit(2);

    productService.addProduct(product1);
    productService.addProduct(product2);

    productsList.add(product1);
    productsList.add(product2);

    Mockito.doReturn(productsList)
        .when(productRepository)
        .findByCategory_CategoryId(600L);

    assertEquals(productsList, productService.findByCategory(600L));
  }


  @Test
  void deleteProduct() {
    Product product = new Product();
    product.setProductId(1L);
    product.setProductName("some product");

    productService.addProduct(product);

    productService.deleteProduct(1L);

    verify(productRepository, times(1)).deleteById(1L);
  }
}
