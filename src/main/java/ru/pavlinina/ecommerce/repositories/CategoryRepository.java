package ru.pavlinina.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pavlinina.ecommerce.models.Category;

/**
 * @author Sofia Pavlinina
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
