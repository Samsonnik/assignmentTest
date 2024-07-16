package by.finby.assignmentTest.controller;

import by.finby.assignmentTest.dto.ProductDTO;
import by.finby.assignmentTest.exceptions.ProductWrongDataException;
import by.finby.assignmentTest.model.Product;
import by.finby.assignmentTest.service.ProductService;
import by.finby.assignmentTest.utill.Convertor;
import by.finby.assignmentTest.utill.PhotoManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@RestController
@RequestMapping("/products-store")
public class MainController {

    private final ProductService productService;
    private final Convertor convertor;
    private final PhotoManager photoManager;

    public MainController(ProductService productService, Convertor convertor, PhotoManager photoManager) {
        this.productService = productService;
        this.convertor = convertor;
        this.photoManager = photoManager;
    }

    @GetMapping("/products")
    public ResponseEntity<?> findAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<?> findProductById(@PathVariable Integer id) {
        if (productService.getProductById(id) != null) {
            return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
        } else return new ResponseEntity<>("Your id is not correct", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/products")
    public ResponseEntity<?> addProduct(@RequestBody ProductDTO productDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(ProductWrongDataException.DEFAULT_MESSAGE_WRONG_DATA, HttpStatus.BAD_REQUEST);
        }
        Product product = convertor.convertDto(productDTO);
        productService.addProduct(product);
        return new ResponseEntity<>("Your product was added", HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {
        if (productService.getProductById(id) != null) {
            productService.removeProduct(id);
            return new ResponseEntity<>("your product was deleted", HttpStatus.OK);
        } else return new ResponseEntity<>(
                new ProductWrongDataException(ProductWrongDataException.DEFAULT_MESSAGE_WRONG_DATA), HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("/products/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody ProductDTO productDTO, @PathVariable Integer id) {
        if (productService.getProductById(id) != null) {
            Product product = convertor.convertDto(productDTO);
            product.setId(id);
            productService.updateProduct(product);
            return new ResponseEntity<>("Your product was updated", HttpStatus.OK);
        } else return new ResponseEntity<>(
                new ProductWrongDataException(ProductWrongDataException.DEFAULT_MESSAGE_WRONG_DATA), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/changeToken")
    public ResponseEntity<?> changeToken(@RequestParam("token") String token) {
        photoManager.setTOKEN(token);
        return new ResponseEntity<>("Token was changed", HttpStatus.OK);
    }
}
