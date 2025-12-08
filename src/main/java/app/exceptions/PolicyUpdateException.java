package app.exceptions;

public class PolicyUpdateException extends RuntimeException {
    public PolicyUpdateException(String message) {
        super(message);
    }
}
