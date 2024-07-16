package by.finby.assignmentTest.exceptions;

public class ProductWrongDataException extends RuntimeException {

    public static final String  DEFAULT_MESSAGE_WRONG_DATA = "Your product has a wrong data. Please, " +
            "check it and try once more";

    public ProductWrongDataException(String message) {
        super(message);
    }
}
