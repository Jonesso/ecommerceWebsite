package ru.pavlinina.ecommerce.services;

import ru.pavlinina.ecommerce.models.Product;

import java.util.List;

/**
 * @author Sofia Pavlinina
 */

public interface ProductService {

    public void addProduct(Product product);

    public List<Product> listProduct();
}
