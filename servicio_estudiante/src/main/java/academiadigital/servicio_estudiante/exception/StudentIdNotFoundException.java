package academiadigital.servicio_estudiante.exception;

public class StudentIdNotFoundException extends RuntimeException {
    public StudentIdNotFoundException(Long id, String message) {
        super(message);
    }
}
