package ru.pavlinina.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pavlinina.ecommerce.models.Product;

import java.util.List;

/**
 * @author Sofia Pavlinina
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    public List<Product> findByCategory_CategoryId(long CategoryId);
}
