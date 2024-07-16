package by.finby.assignmentTest.controller;

import by.finby.assignmentTest.model.Product;
import by.finby.assignmentTest.service.ProductPhotoService;
import by.finby.assignmentTest.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/products-store")
public class ControllerWithPresentation {

    private final ProductService productService;
    private final ProductPhotoService productPhotoService;

    public ControllerWithPresentation(ProductService productService, ProductPhotoService productPhotoService) {
        this.productService = productService;
        this.productPhotoService = productPhotoService;
    }

    @GetMapping("/allProducts")
    public String findAllProductsAndShowInTable(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "Products";
    }

    @GetMapping("/product")
    public String findProductWithParam(@RequestParam(name = "query") String query, Model model) {
        Product product = new Product();
        if (query.equals("expensive")) {
            product = productService.findTheMostExpensive();
        }

        if (query.equalsIgnoreCase("unexpensive")) {
            product = productService.findTheMostUnExpensive();
        }
        if (query.equalsIgnoreCase("rate")) {
            product = productService.findTheMostRate();
        }
        model.addAttribute("product", product);
        return "Product";
    }

    @GetMapping("/{id}")
    public String getProduct(@PathVariable Integer id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("productPhotos", productPhotoService.getAllWithTheSameProductPhotoOwnerId(id));
        return "Product";
    }
}
