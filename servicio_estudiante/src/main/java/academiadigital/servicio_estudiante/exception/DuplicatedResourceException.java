package academiadigital.servicio_estudiante.exception;

public class DuplicatedResourceException extends RuntimeException {
    public DuplicatedResourceException(String email, String message) {
        super(message);
    }
}
