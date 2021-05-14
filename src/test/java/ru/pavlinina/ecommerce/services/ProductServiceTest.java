package ru.pavlinina.ecommerce.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.pavlinina.ecommerce.models.Product;
import ru.pavlinina.ecommerce.repositories.ProductRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void saveProduct() {
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
    }
}
