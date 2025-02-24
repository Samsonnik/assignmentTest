package by.finby.assignmentTest.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "products_photo")
public class ProductPhotoModel {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "photo_product")
    private Integer photoProductOwner;

    @Column(name = "photo")
    private String photoUrl;
}
