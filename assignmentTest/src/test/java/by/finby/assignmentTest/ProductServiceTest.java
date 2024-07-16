package by.finby.assignmentTest;

import by.finby.assignmentTest.exceptions.ProductNotFoundException;
import by.finby.assignmentTest.model.Product;
import by.finby.assignmentTest.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductServiceTest {

    private final ProductService productService;
    private Product product;

    @Autowired
    public ProductServiceTest(ProductService productService) {
        this.productService = productService;
    }

    @BeforeEach
    public void setDataForTesting() {
        product = new Product();
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

        productService.addProduct(product2);
        productService.addProduct(product);
    }

    @Test
    public void testGetProductById() {
        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(2));
        assertEquals(productService.getProductById(1).getProductName(), "product name");
    }

    @Test
    public void testAddProduct() {
        assertNotNull(productService.getProductById(1));
        assertEquals(productService.getProductById(1), product);
    }

    @Test
    public void testUpdateProduct() {
        product.setProductName("updName");

        productService.updateProduct(product);
        assertEquals(productService.getProductById(1).getProductName(), "updName");
        assertEquals(productService.getProductById(1).getAvailableQuantity(), 2);

        product.setAvailableQuantity(4);
        productService.updateProduct(product);
        assertEquals(productService.getProductById(1).getAvailableQuantity(), 4);
    }

    @Test
    public void testFindTheMostExpensive() {
        assertEquals(productService.findTheMostExpensive().getPrice(), 99999.4);
    }

    @Test
    public void testFindTheMostUnExpensive() {
        assertEquals(productService.findTheMostUnExpensive().getPrice(), 0.1);
    }

    @Test
    public void testFindTheMostRate() {
        assertEquals(productService.findTheMostRate().getRating(), 999);
    }

    @Test
    public void testRemoveProduct() {
        productService.removeProduct(1);
        productService.removeProduct(5);
        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(1));
        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(5));
    }
}
