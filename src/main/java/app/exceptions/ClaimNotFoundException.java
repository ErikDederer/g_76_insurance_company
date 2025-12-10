package app.exceptions;

public class ClaimNotFoundException extends RuntimeException {
    public ClaimNotFoundException(Long id) {
        super(String.format("Заявка с id %d не найдена", id));
    }
}
