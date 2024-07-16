package by.finby.assignmentTest.exceptions;

public class ProductNotFoundException extends RuntimeException{

    public static final String  DEFAULT_MESSAGE_NOT_FOUND = "The product with this id isn't exist";

    public ProductNotFoundException(String message) {
        super(message);
    }
}
