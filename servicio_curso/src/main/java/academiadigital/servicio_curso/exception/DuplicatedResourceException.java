package academiadigital.servicio_curso.exception;

public class DuplicatedResourceException extends RuntimeException{
    public DuplicatedResourceException(String message){
        super(message);
    }
}
