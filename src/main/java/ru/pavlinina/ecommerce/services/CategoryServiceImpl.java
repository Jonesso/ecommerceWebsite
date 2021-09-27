package ru.pavlinina.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pavlinina.ecommerce.models.Category;
import ru.pavlinina.ecommerce.repositories.CategoryRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * implementation of {@link ru.pavlinina.ecommerce.services.CategoryService}
 * @author Sofia Pavlinina
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public List<Category> listCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public void deleteCategory(long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public void updateCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Optional<Category> getCategory(long categoryId) {
        return categoryRepository.findById(categoryId);
    }
}
