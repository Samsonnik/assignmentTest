package by.finby.assignmentTest.service;

import by.finby.assignmentTest.model.ProductPhotoModel;
import by.finby.assignmentTest.repositories.ProductPhotoModelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductPhotoService {

    private final ProductPhotoModelRepository productPhotoModelRepository;


    public ProductPhotoService(ProductPhotoModelRepository productPhotoRepository) {
        this.productPhotoModelRepository = productPhotoRepository;
    }

    public List<ProductPhotoModel> getAllWithTheSameProductPhotoOwnerId(Integer id) {
        return productPhotoModelRepository.getProductPhotoModelsByPhotoProductOwner(id);
    }

    public void deleteAllProductPhotoModelsById(Integer id) {
        productPhotoModelRepository.deleteProductPhotoModelsByPhotoProductOwner(id);
    }

    public ProductPhotoModel addProductPhotoModel(ProductPhotoModel productPhotoModel) {
       return productPhotoModelRepository.save(productPhotoModel);
    }

    public List<ProductPhotoModel> findAll() {
        return productPhotoModelRepository.findAll();
    }
}
