package academiadigital.servicio_curso.service;

import academiadigital.servicio_curso.dto.CourseResponseDto;
import academiadigital.servicio_curso.dto.EnrollmentRequestDto;
import academiadigital.servicio_curso.dto.EnrollmentResponseDto;

public interface EnrollmentService {
    //CRUD
    EnrollmentResponseDto makeEnrollment(EnrollmentRequestDto requestDto);
    void deleteEnrollment(Long studentId, Long courseId);

    //Queda pendiente crear la eliminacion en cascada, si no existe un curso no puede haber inscripciones
    //Eliminar un estudiante de un curso (relacion de inscripciones)
    //Listado de estudiantes dentro de un curso (Como puedo obtener los nombres?)

}
