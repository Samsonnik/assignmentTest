package by.finby.assignmentTest;

import by.finby.assignmentTest.controller.MainController;
import by.finby.assignmentTest.model.Product;
import by.finby.assignmentTest.service.ProductService;
import by.finby.assignmentTest.utill.Convertor;
import by.finby.assignmentTest.utill.PhotoManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MainControllerTest {
    @Mock
    private ProductService productService;
    @Mock
    private PhotoManager photoManager;
    @Mock
    private Convertor convertor;

    private List<Product> productList = new ArrayList<>();

    @BeforeEach
    public void setDataForTesting() {
        Product product = new Product();
        product.setId(1);
        product.setProductBrand("brand");
        product.setProductName("product name");
        product.setProperties("property");
        product.setPhoto("photo url");
        product.setPrice(0.1);
        product.setColor("black");
        product.setCategory("category");
        product.setDescription("description");
        product.setPhotoTitle("title");
        product.setRating(33);
        product.setAvailableQuantity(2);

        Product product2 = new Product();
        product2.setId(5);
        product2.setProductBrand("5 brand");
        product2.setProductName("5 product name");
        product2.setProperties("5 property");
        product2.setPhoto("5 photo url");
        product2.setPrice(99999.4);
        product2.setColor("5 black");
        product2.setCategory("category");
        product2.setDescription("description");
        product2.setPhotoTitle("title");
        product2.setRating(999);
        product2.setAvailableQuantity(2);

        productList.add(product);
        productList.add(product2);
    }

    @Test
    public void testGetAllProducts() {
        Mockito.when(productService.getAllProducts()).thenReturn(productList);
        MainController mainController = new MainController(productService, convertor, photoManager);
        assertEquals(new ResponseEntity<>(productList, HttpStatus.OK), mainController.findAllProducts());
    }

    @Test
    public void testFindProductById() {
        Mockito.when(productService.getProductById(2)).thenReturn(productList.get(0));
        MainController mainController = new MainController(productService, convertor, photoManager);
        assertEquals(new ResponseEntity<>(productList.get(0), HttpStatus.OK), mainController.findProductById(2));
    }
}
