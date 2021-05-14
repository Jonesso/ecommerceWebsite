package ru.pavlinina.ecommerce.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.pavlinina.ecommerce.models.Category;
import ru.pavlinina.ecommerce.models.Product;
import ru.pavlinina.ecommerce.repositories.CategoryRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Sofia Pavlinina
 */
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @MockBean
    private CategoryRepository categoryRepository;

    @Test
    void updateCategory() {
        Category category = new Category();

        category.setCategoryId(600);
        category.setCategoryName("Test");

        categoryService.addCategory(category);

        assertEquals(600L, category.getCategoryId());
        assertEquals("Test", category.getCategoryName());

        category.setCategoryName("Test2");

        categoryService.updateCategory(category);

        assertEquals(600L, category.getCategoryId());
        assertEquals("Test2", category.getCategoryName());

        Mockito.doReturn(Optional.of(category))
                .when(categoryRepository)
                .findById(600L);
    }
}
