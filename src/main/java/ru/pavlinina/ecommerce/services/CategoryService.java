package ru.pavlinina.ecommerce.services;

import ru.pavlinina.ecommerce.models.Category;

import java.util.List;

/**
 * @author Sofia Pavlinina
 */
public interface CategoryService {

    public void addCategory(Category category);

    public List<Category> listCategory();
}
