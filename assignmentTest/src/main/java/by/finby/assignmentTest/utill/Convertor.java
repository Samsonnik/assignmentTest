package by.finby.assignmentTest.utill;

import by.finby.assignmentTest.dto.ProductDTO;
import by.finby.assignmentTest.model.Product;
import by.finby.assignmentTest.model.ProductPhotoModel;
import by.finby.assignmentTest.service.ProductPhotoService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class Convertor {

    private final PhotoManager photoManager;
    private final ProductPhotoService productPhotoService;
    private final Random random = new Random();
    private final Integer MAX_VALUE = Integer.MAX_VALUE;

    public Convertor(PhotoManager photoManager, ProductPhotoService productPhotoService) {
        this.photoManager = photoManager;
        this.productPhotoService = productPhotoService;
    }

    public Product convertDto(ProductDTO productDTO) {
        Product product = new Product();
        int id = random.nextInt(MAX_VALUE);
        product.setId(id);
        product.setProductName(productDTO.getProductName());
        product.setProductBrand(productDTO.getProductBrand());
        product.setAvailableQuantity(productDTO.getAvailableQuantity());
        product.setWeight(productDTO.getWeight());
        product.setRating(productDTO.getRating());
        product.setCategory(productDTO.getCategory());
        product.setDescription(setDescription(productDTO));
        product.setColor(productDTO.getColor());
        product.setPrice(productDTO.getPrice());
        product.setPhotoTitle(productDTO.getPhotoTitle());

        List<String> productPhotoUrlList = photoManager.managePhoto(productDTO.getPhoto());
        productPhotoUrlList.forEach(productPhoto -> {
            ProductPhotoModel productPhotoModel = new ProductPhotoModel();
            productPhotoModel.setId(random.nextInt(MAX_VALUE));
            productPhotoModel.setPhotoUrl(productPhoto);
            productPhotoModel.setPhotoProductOwner(id);
            productPhotoService.addProductPhotoModel(productPhotoModel);
        });

        product.setPhoto(productPhotoUrlList.get(0));

        String properties = productDTO.getProperties().toString();
        product.setProperties(properties.substring(1, properties.length() - 1));
        return product;
    }

    private String setDescription(ProductDTO productDTO) {
        String description = "";
        if (productDTO.getDescription() != null) {
            description = productDTO.getDescription();
        }

        if (productDTO.getCategory().contains("phone")) {
            String autoDescription = String.format(
                    "Get a phone as unique as your style with the exclusive %s. This incredible full sized " +
                            "smartphone. Brand: %s, Color: %s, weight: %s. You should buy it just for %s $. Hurry Up !",
                    productDTO.getProductName(), productDTO.getProductBrand(), productDTO.getColor(),
                    productDTO.getWeight(), productDTO.getPrice());
            return autoDescription + description;
        }

        if (productDTO.getCategory().contains("computer")) {
           String autoDescription = String.format(
                    "Premium components that can be upgraded and customized to fit your specific needs and budget. " +
                            "Name: %s, Brand: %s, Color: %s, weight: %s. You should buy it just for %s $. Hurry Up !",
                    productDTO.getProductName(), productDTO.getProductBrand(), productDTO.getColor(),
                    productDTO.getWeight(), productDTO.getPrice());
           return autoDescription + description;
        } else {
            String autoDescription = String.format(
                    "Can’t stop buying useless products? Unbeleafable. Cover yourself in your " +
                            "favourite obsession and buy this pretty %s.”. Brand: %s, Color: %s, weight: %s. You " +
                            "should buy it just for %s $. Hurry Up !",
                    productDTO.getProductName(), productDTO.getProductBrand(), productDTO.getColor(),
                    productDTO.getWeight(), productDTO.getPrice());
            return autoDescription + description;
        }
    }
}
