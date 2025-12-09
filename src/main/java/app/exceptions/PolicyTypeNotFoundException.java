package app.exceptions;

public class PolicyTypeNotFoundException extends RuntimeException {

    public PolicyTypeNotFoundException(String type) {
        super(String.format(
                "Некорректный тип полиса: '%s'. Допустимые значения: AUTO, HEALTH, PROPERTY",
                type
        ));
    }
}