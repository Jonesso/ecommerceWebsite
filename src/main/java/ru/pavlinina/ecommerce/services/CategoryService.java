package ru.pavlinina.ecommerce.services;

import ru.pavlinina.ecommerce.models.Category;

import java.util.List;
import java.util.Optional;

/**
 * service for operating with Category table entities
 * @author Sofia Pavlinina
 */
public interface CategoryService {

    /**
     * method for adding new entity to Category table
     * @param category entity to add to table
     */
    public void addCategory(Category category);

    /**
     * method for returning all categories from table
     * @return list of all entities from Category table
     */
    public List<Category> listCategory();

    /**
     * method for removing entity from Category table
     * @param categoryId id of entity to delete
     */
    public void deleteCategory(long categoryId);

    /**
     * method for updating Category table entity's data
     * @param category entity with updated data to change the old one
     */
    public void updateCategory(Category category);

    /**
     * method for getting Category table entity
     * @param categoryId id of necessary Category table entity
     * @return necessary entity, if it exists, or null, if not
     */
    public Optional<Category> getCategory(long categoryId);

}
