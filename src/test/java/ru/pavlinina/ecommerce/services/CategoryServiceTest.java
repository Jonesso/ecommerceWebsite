package ru.pavlinina.ecommerce.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.pavlinina.ecommerce.models.Category;
import ru.pavlinina.ecommerce.repositories.CategoryRepository;

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

    category.setCategoryId(600L);
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

  @Test
  void deleteCategory() {
    Category category = new Category();

    category.setCategoryId(123L);
    category.setCategoryName("delet me");

    categoryService.addCategory(category);

    categoryService.deleteCategory(123L);

    verify(categoryRepository, times(1)).deleteById(123L);
  }

  @Test
  void addCategory() {
    Category category = new Category();

    category.setCategoryId(200L);
    category.setCategoryName("new set category");

    categoryService.addCategory(category);

    Mockito.verify(categoryRepository, Mockito.times(1)).save(category);
  }

  @Test
  void getCategory() {
    Category category = new Category();

    category.setCategoryId(111L);
    category.setCategoryName("new category to get");

    categoryService.addCategory(category);

    assertEquals(111L, category.getCategoryId());
    assertEquals("new category to get", category.getCategoryName());
  }

  @Test
  void listCategory() {
    Category category1 = new Category();
    Category category2 = new Category();
    List<Category> categoriesList = new ArrayList<>();

    category1.setCategoryId(1L);
    category1.setCategoryName("first category");
    category2.setCategoryId(2L);
    category2.setCategoryName("second category");

    categoryService.addCategory(category1);
    categoryService.addCategory(category2);

    categoriesList.add(category1);
    categoriesList.add(category2);

    Mockito.doReturn(categoriesList)
        .when(categoryRepository)
        .findAll();

    assertEquals(categoriesList, categoryService.listCategory());
  }
}
