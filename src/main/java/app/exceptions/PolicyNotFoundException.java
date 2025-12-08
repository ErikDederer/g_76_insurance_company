package app.exceptions;

public class PolicyNotFoundException extends RuntimeException {
    public PolicyNotFoundException(Long id) {
        super("Полис с идентификатором %d не найден или не активен".formatted(id));
    }
}
