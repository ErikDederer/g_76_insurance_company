package app.exceptions;

public class PolicySaveException extends RuntimeException {
    public PolicySaveException(String message) {
        super(message);
    }
}
