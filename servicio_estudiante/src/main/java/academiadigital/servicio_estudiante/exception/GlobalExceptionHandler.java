package academiadigital.servicio_estudiante.exception;

import academiadigital.servicio_estudiante.dto.ErrorResponseDto;
import academiadigital.servicio_estudiante.util.ApiConstants;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //Manejado de errores de validacion general (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleStudentIdNotFoundException(
       MethodArgumentNotValidException exception,
       HttpServletRequest request
    ){
        List<String> errors = exception.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());

        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                ApiConstants.VALIDATE_ERR_GENERAL,
                errors,
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StudentEmailNotFoundException.class)
    //Manejador para response 404
    public ResponseEntity<ErrorResponseDto> handlerStudentEmailNotFound(
            StudentEmailNotFoundException exception,
            HttpServletRequest request
    ){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                ApiConstants.VALIDATE_ERR_EMAIL_NOT_FOUND,
                List.of(exception.getMessage()),
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
    }

    //Manejador de conflictos 409 (Duplicidad)
    @ExceptionHandler(DuplicatedResourceException.class)
    public ResponseEntity<ErrorResponseDto> handlerDuplicatedResourceException(
            DuplicatedResourceException exception,
            HttpServletRequest request
    ){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                ApiConstants.VALIDATE_ERR_DUPLICATED_RESOURCE,
                List.of(exception.getMessage()),
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponseDto, HttpStatus.CONFLICT);
    }

    //Manejador generico codigo 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> hanlderGlobalException(
            Exception exception,
            HttpServletRequest request
    ){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ApiConstants.VALIDATE_ERR_SERVER_ERROR,
                List.of(ApiConstants.VALIDATE_ERR_UNEXPECTED, exception.getMessage()),
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
