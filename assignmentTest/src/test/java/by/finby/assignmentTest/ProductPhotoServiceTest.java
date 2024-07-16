package by.finby.assignmentTest;

import by.finby.assignmentTest.model.ProductPhotoModel;
import by.finby.assignmentTest.service.ProductPhotoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ProductPhotoServiceTest {

    private final ProductPhotoService productPhotoService;

    @Autowired
    ProductPhotoServiceTest(ProductPhotoService productPhotoService) {
        this.productPhotoService = productPhotoService;
    }

    @BeforeEach
    public void setDataForTesting() {
        ProductPhotoModel productPhotoModel0 = new ProductPhotoModel();
        productPhotoModel0.setId(1);
        productPhotoModel0.setPhotoProductOwner(22);
        productPhotoModel0.setPhotoUrl("123.jpg");

        ProductPhotoModel productPhotoModel1 = new ProductPhotoModel();
        productPhotoModel1.setId(2);
        productPhotoModel1.setPhotoProductOwner(22);
        productPhotoModel1.setPhotoUrl("1234.jpg");

        ProductPhotoModel productPhotoModel2 = new ProductPhotoModel();
        productPhotoModel2.setId(3);
        productPhotoModel2.setPhotoProductOwner(33);
        productPhotoModel2.setPhotoUrl("12345.jpg");

        ProductPhotoModel productPhotoModel3 = new ProductPhotoModel();
        productPhotoModel3.setId(4);
        productPhotoModel3.setPhotoProductOwner(33);
        productPhotoModel3.setPhotoUrl("123456.jpg");

        ProductPhotoModel productPhotoModel4 = new ProductPhotoModel();
        productPhotoModel4.setId(5);
        productPhotoModel4.setPhotoProductOwner(44);
        productPhotoModel4.setPhotoUrl("1234567.jpg");

        productPhotoService.addProductPhotoModel(productPhotoModel0);
        productPhotoService.addProductPhotoModel(productPhotoModel1);
        productPhotoService.addProductPhotoModel(productPhotoModel2);
        productPhotoService.addProductPhotoModel(productPhotoModel3);
        productPhotoService.addProductPhotoModel(productPhotoModel4);
    }

    @Test
    void testGetAllWithTheSameProductPhotoOwnerId() {
        List<ProductPhotoModel> photoModels = productPhotoService.getAllWithTheSameProductPhotoOwnerId(22);
        assertEquals(photoModels.size(), 2);
        for (ProductPhotoModel productPhotoModel : photoModels) {
            assertEquals(productPhotoModel.getPhotoProductOwner(), 22);
        }
    }

    @Test
    void TestAddProductPhotoModel() {
        ProductPhotoModel productPhotoModel = new ProductPhotoModel();
        productPhotoModel.setId(55);
        productPhotoModel.setPhotoProductOwner(100);
        productPhotoModel.setPhotoUrl("100.jpg");

        productPhotoService.addProductPhotoModel(productPhotoModel);
        List<ProductPhotoModel> photoModels = productPhotoService.getAllWithTheSameProductPhotoOwnerId(100);
        for (ProductPhotoModel tempProductPhotoModel : photoModels) {
            assertEquals(tempProductPhotoModel.getPhotoProductOwner(), 100);
        }
    }
}
