package by.finby.assignmentTest.service;

import by.finby.assignmentTest.exceptions.ProductNotFoundException;
import by.finby.assignmentTest.exceptions.ProductWrongDataException;
import by.finby.assignmentTest.model.Product;
import by.finby.assignmentTest.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements ProductServiceInterface {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product addProduct(Product product) {
        if ((product.getId() > 0) && (!product.getProductName().equals(" ")) && (!product.getProductBrand().equals(" "))) {
            return productRepository.save(product);
        } else {
            throw new ProductWrongDataException(ProductWrongDataException.DEFAULT_MESSAGE_WRONG_DATA);
        }
    }

    @Override
    public Product updateProduct(Product product) {
        Optional<Product> currentProduct = productRepository.findById(product.getId());
        if (currentProduct.isPresent()) {
            productRepository.deleteById(product.getId());
            return productRepository.save(product);
        } else {
            throw new ProductNotFoundException(ProductNotFoundException.DEFAULT_MESSAGE_NOT_FOUND);
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(int id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        } else {
            throw new ProductNotFoundException(ProductNotFoundException.DEFAULT_MESSAGE_NOT_FOUND);
        }
    }

    @Override
    public void removeProduct(int id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.deleteById(id);
        } else {
            throw new ProductNotFoundException(ProductNotFoundException.DEFAULT_MESSAGE_NOT_FOUND);
        }
    }

    public Product findTheMostExpensive() {
        return productRepository.findTopByOrderByPriceDesc();
    }
    public Product findTheMostUnExpensive() {
        return productRepository.findTopByOrderByPriceAsc();
    }

    public Product findTheMostRate() {
        return productRepository.findTopByOrderByRatingDesc();
    }
}
