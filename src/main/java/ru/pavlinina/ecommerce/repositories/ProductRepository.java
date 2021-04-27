package ru.pavlinina.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pavlinina.ecommerce.models.Product;

/**
 * @author Sofia Pavlinina
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
