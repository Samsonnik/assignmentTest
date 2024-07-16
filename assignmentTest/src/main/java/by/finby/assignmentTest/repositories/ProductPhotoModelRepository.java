package by.finby.assignmentTest.repositories;

import by.finby.assignmentTest.model.ProductPhotoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductPhotoModelRepository extends JpaRepository<ProductPhotoModel, Integer> {
    List<ProductPhotoModel> getProductPhotoModelsByPhotoProductOwner(Integer id);
    void deleteProductPhotoModelsByPhotoProductOwner(Integer id);
}
