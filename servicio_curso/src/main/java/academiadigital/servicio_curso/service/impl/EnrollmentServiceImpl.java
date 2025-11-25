package academiadigital.servicio_curso.service.impl;

import academiadigital.servicio_curso.client.StudentFeingClient;
import academiadigital.servicio_curso.dto.EnrollmentRequestDto;
import academiadigital.servicio_curso.dto.EnrollmentResponseDto;
import academiadigital.servicio_curso.dto.StudentResponseDto;
import academiadigital.servicio_curso.exception.CourseIdNotFoundException;
import academiadigital.servicio_curso.exception.ResourceNotFoundException;
import academiadigital.servicio_curso.mapper.EnrollmentMapper;
import academiadigital.servicio_curso.model.Enrollment;
import academiadigital.servicio_curso.repository.CourseRepository;
import academiadigital.servicio_curso.repository.EnrollmentRepository;
import academiadigital.servicio_curso.service.CourseService;
import academiadigital.servicio_curso.service.EnrollmentService;
import academiadigital.servicio_curso.util.ApiConstants;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class EnrollmentServiceImpl implements EnrollmentService {
    @Autowired
    private final StudentFeingClient studentFeingClient;
    @Autowired
    private final EnrollmentRepository enrollmentRepository;
    @Autowired
    private final EnrollmentMapper enrollmentMapper;
    @Autowired
    private final CourseService courseService;
    @Autowired
    private final CourseRepository courseRepository;

    @Override
    public EnrollmentResponseDto makeEnrollment(EnrollmentRequestDto requestDto) {
        try {
            log.info("Verificando existencia de curso con ID: {}", requestDto.courseId());
            courseService.getCourseById(requestDto.courseId());
            log.info("Curso con ID: {} encontrado", requestDto.courseId());
        }catch (CourseIdNotFoundException exception){
            log.info("Curso con ID: {} no existe", requestDto.courseId());
            throw new ResourceNotFoundException(ApiConstants.BUSINESS_ERR_ID_NOT_FOUND);
        }

        try{
            log.info("Verificando existencia de estudiante con ID: {}", requestDto.studentId());
            StudentResponseDto studentResponseDto = studentFeingClient.getStudenById(requestDto.studentId());
            Enrollment enrollment = new Enrollment();
            enrollment.setCourseId(requestDto.courseId());
            enrollment.setStudentId(requestDto.studentId());
            enrollment.setEnrollmentDate(LocalDateTime.now());
            Enrollment newEnrollment = enrollmentRepository.save(enrollment);

            log.info("Estudiante registrado en el curso con ID: {}", requestDto.courseId());
            return enrollmentMapper.mapToEnrollmentDto(newEnrollment);
        }catch(FeignException.NotFound error){
            throw new ResourceNotFoundException(ApiConstants.BUSINESS_ERR_ID_NOT_FOUND);
        }
    }

    public List<Long> getStudentsByCourse(Long courseId){
        //Se verifica si el curso existe
        if(!courseRepository.existsById(courseId)){
            throw new ResourceNotFoundException(ApiConstants.BUSINESS_ERR_ID_NOT_FOUND);
        }
        List<Enrollment> enrollmentList = enrollmentRepository.findByCourseId(courseId);
        //Uso de Streams para mapear la lista de objetos Inscripcion a una Lista de Long (Solos los Ids)
        return enrollmentList.stream()
                .map(Enrollment::getStudentId)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteEnrollment(Long studentId, Long courseId) {
        log.info("Intentando eliminar inscripción de Estudiante ID: {} del Curso ID: {}", studentId, courseId);
        Optional<Enrollment> enrollmentCheck = enrollmentRepository.findByStudentIdAndCourseId(studentId, courseId);
        if (enrollmentCheck.isEmpty()){
            log.warn("Inscripción no encontrada para Estudiante ID: {} y Curso ID: {}", studentId, courseId);
            throw new ResourceNotFoundException(ApiConstants.BUSINESS_ERR_ID_NOT_FOUND);
        }
        log.info("Inscripción eliminada exitosamente.");
        enrollmentRepository.delete(enrollmentCheck.get());
    }
}
