package ru.pavlinina.ecommerce.services;

import ru.pavlinina.ecommerce.models.Product;

import java.util.List;
import java.util.Optional;

/**
 * service for operating with Product table entities
 * @author Sofia Pavlinina
 */

public interface ProductService {

    /**
     * method for adding new entity to Product table
     * @param product entity to add to table
     */
    public void addProduct(Product product);

    /**
     * method for returning all products from table
     * @return list of all entities from Product table
     */
    public List<Product> listProduct();

    /**
     * method for getting Product table entity
     * @param productId id of necessary Product table entity
     * @return necessary entity, if it exists, or null, if not
     */
    public Optional<Product> getProductById(long productId);

    /**
     * method for getting all products of necessary category
     * @param categoryId id of necessary Category table entity
     * @return list of Product table entities, which refer to Category table entity
     */
    public List<Product> findByCategory(long categoryId);

    /**
     * method for removing entity from Product table
     * @param productId id of entity to delete
     */
    public void deleteProduct(long productId);
}
