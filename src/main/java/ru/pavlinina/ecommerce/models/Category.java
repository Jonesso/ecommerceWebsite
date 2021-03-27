package ru.pavlinina.ecommerce.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sofia Pavlinina
 */
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long categoryId;

    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<Product> productList = new ArrayList<Product>();

}
