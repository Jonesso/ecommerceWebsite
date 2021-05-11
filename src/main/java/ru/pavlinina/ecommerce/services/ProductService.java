package ru.pavlinina.ecommerce.services;

import ru.pavlinina.ecommerce.models.Product;

import java.util.List;
import java.util.Optional;

/**
 * @author Sofia Pavlinina
 */

public interface ProductService {

    public void addProduct(Product product);

    public List<Product> listProduct();

    public Optional<Product> getProductById(long productId);

    public List<Product> findByCategory(long categoryId);

    public void deleteProduct(long productId);
}
