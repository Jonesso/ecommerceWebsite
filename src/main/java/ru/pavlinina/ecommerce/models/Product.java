package ru.pavlinina.ecommerce.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sofia Pavlinina
 */
@Entity
@Table(name = "product")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long productId;

    @Column
    private String productName;

    private String productDescription;

    @Column
    private int productPrice;

    @Column
    private int productUnit;

    private String image;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id")
    private Category category = new Category();

    @ManyToMany(mappedBy = "productList", fetch = FetchType.EAGER)
    private List<User> userList;

    public Product() {
        this.productName = "";
        this.productDescription = "";
    }

}
