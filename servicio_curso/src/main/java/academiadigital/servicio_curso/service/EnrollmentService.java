package academiadigital.servicio_curso.service;

import academiadigital.servicio_curso.dto.CourseResponseDto;
import academiadigital.servicio_curso.dto.EnrollmentRequestDto;
import academiadigital.servicio_curso.dto.EnrollmentResponseDto;

import java.util.List;

public interface EnrollmentService {
    //CRUD
    List<Long> getStudentsByCourse(Long courseId);
    EnrollmentResponseDto makeEnrollment(EnrollmentRequestDto requestDto);
    void deleteEnrollment(Long studentId, Long courseId);

    //Listado de estudiantes dentro de un curso (Como puedo obtener los nombres?)

}
