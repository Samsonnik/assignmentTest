package by.finby.assignmentTest.dto;


import lombok.Data;

import java.util.List;

@Data
public class ProductDTO {

    private String productName;

    private String productBrand;

    private Integer availableQuantity;

    private String weight;

    private Integer rating;

    private String category;

    private String description;

    private String color;

    private Double price;

    private String photoTitle;

    private List<String> photo;

    private List<String> properties;
}
