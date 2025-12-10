package app.exceptions;

public class ClaimSaveException extends RuntimeException {
    public ClaimSaveException(String message) {
        super(message);
    }
}
