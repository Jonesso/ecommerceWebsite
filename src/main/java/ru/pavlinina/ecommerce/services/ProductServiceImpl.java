package ru.pavlinina.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pavlinina.ecommerce.models.Product;
import ru.pavlinina.ecommerce.repositories.ProductRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Sofia Pavlinina
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> listProduct() {
        return productRepository.findAll();
    }
}
