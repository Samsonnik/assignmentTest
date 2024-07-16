package by.finby.assignmentTest.repositories;

import by.finby.assignmentTest.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findByProductName(String productName);
    Product findTopByOrderByPriceAsc();

    Product findTopByOrderByPriceDesc();

    Product findTopByOrderByRatingDesc();

}
