package ru.pavlinina.ecommerce.services;

import ru.pavlinina.ecommerce.models.Category;

import java.util.List;
import java.util.Optional;

/**
 * @author Sofia Pavlinina
 */
public interface CategoryService {

    public void addCategory(Category category);

    public List<Category> listCategory();

    public void deleteCategory(long categoryId);

    public void updateCategory(Category category);

    public Optional<Category> getCategory(long categoryId);

}
