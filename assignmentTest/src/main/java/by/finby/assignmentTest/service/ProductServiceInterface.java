package by.finby.assignmentTest.service;

import by.finby.assignmentTest.model.Product;

import java.util.List;

public interface ProductServiceInterface {

    Product addProduct(Product product);

    Product updateProduct(Product product);

    List<Product> getAllProducts();

    Product getProductById(int id);

    void removeProduct(int id);
}
