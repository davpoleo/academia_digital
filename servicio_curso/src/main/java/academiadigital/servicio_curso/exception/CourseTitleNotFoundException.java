package academiadigital.servicio_curso.exception;

public class CourseTitleNotFoundException extends RuntimeException{
    public CourseTitleNotFoundException(String message){
        super(message);
    }
}
