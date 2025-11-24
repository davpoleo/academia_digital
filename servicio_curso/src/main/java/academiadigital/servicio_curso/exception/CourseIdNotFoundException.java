package academiadigital.servicio_curso.exception;

public class CourseIdNotFoundException extends RuntimeException{
    public CourseIdNotFoundException(String message){
        super(message);
    }
}
