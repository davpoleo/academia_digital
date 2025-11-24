package academiadigital.servicio_estudiante.exception;

public class StudentEmailNotFoundException extends RuntimeException {
    public StudentEmailNotFoundException(String email, String message) {
        super(message);
    }
}
