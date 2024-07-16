package by.finby.assignmentTest.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_brand")
    private String productBrand;

    @Column(name = "available_quantity")
    private Integer availableQuantity;

    @Column(name = "weight")
    private String weight;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "category")
    private String category;

    @Column(name = "description")
    private String description;

    @Column(name = "color")
    private String color;

    @Column(name = "price")
    private Double price;

    @Column(name = "photo_title")
    private String photoTitle;

    @Column(name = "photo")
    private String photo;

    @Column(name = "properties")
    private String properties;
}
