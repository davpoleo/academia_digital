package academiadigital.servicio_curso.service.impl;

import academiadigital.servicio_curso.dto.CourseRequestDto;
import academiadigital.servicio_curso.dto.CourseResponseDto;
import academiadigital.servicio_curso.exception.CourseIdNotFoundException;
import academiadigital.servicio_curso.exception.CourseTitleNotFoundException;
import academiadigital.servicio_curso.exception.DuplicatedResourceException;
import academiadigital.servicio_curso.mapper.CourseMapper;
import academiadigital.servicio_curso.model.Course;
import academiadigital.servicio_curso.repository.CourseRepository;
import academiadigital.servicio_curso.repository.EnrollmentRepository;
import academiadigital.servicio_curso.service.CourseService;
import academiadigital.servicio_curso.util.ApiConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final EnrollmentRepository enrollmentRepository;

    @Override
    @Transactional(readOnly = true)
    public CourseResponseDto getCourseById(Long id) {
        log.info("Realizando busqueda de curso por id: {}", id);
        Course searchCourseById = courseRepository.findById(id)
                .orElseThrow(()-> new CourseIdNotFoundException(ApiConstants.BUSINESS_ERR_ID_NOT_FOUND));
        log.info("Curso con id: {} encontrado", id);
        return courseMapper.mapToCourseDto(searchCourseById);
    }

    @Override
    @Transactional(readOnly = true)
    public CourseResponseDto getCourseByTitle(String title) {
        log.info("Realizando busqueda de curso por titulo: {}", title);
        Course searchCourseByTitle = courseRepository.findByTitle(title)
                .orElseThrow(()-> new CourseTitleNotFoundException(ApiConstants.BUSINESS_ERR_TITLE_NOT_FOUND));
        log.info("Curso con el titulo: {} encontrado", title);
        return courseMapper.mapToCourseDto(searchCourseByTitle);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseResponseDto> getAllCourses() {
        log.info("Realizando busqueda de todos los cursos registrados");
        return courseRepository.findAll().stream()
                .map(courseMapper::mapToCourseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CourseResponseDto createCourse(CourseRequestDto requestDto) {
        log.info("Iniciando la creacion del curso titulado: {}", requestDto.title());
        courseRepository.findByTitle(requestDto.title()).ifPresent(course -> {
            throw new DuplicatedResourceException(ApiConstants.BUSINESS_ERR_DUPLICATED_TITLE);
        });
        Course course = new Course();
        course.setTitle(requestDto.title());
        course.setDescription(requestDto.description());
        Course newCourse = courseRepository.save(course);
        log.info("Curso titulado: {} creado satisfactoriamente", requestDto.title());
        return courseMapper.mapToCourseDto(newCourse);
    }

    @Override
    @Transactional
    public CourseResponseDto updateCourse(Long id, CourseRequestDto requestDto) {
        log.info("Iniciando la Actualizacion del curso con id: {}", id);
        Course courseToUpdate = courseRepository.findById(id)
                .orElseThrow(()-> new CourseIdNotFoundException(ApiConstants.BUSINESS_ERR_ID_NOT_FOUND));

        courseRepository.findByTitle(requestDto.title()).ifPresent(course -> {
            if(!course.getId().equals(id)){
                throw new DuplicatedResourceException(ApiConstants.BUSINESS_ERR_DUPLICATED_TITLE);
            }
        });
        courseToUpdate.setTitle(requestDto.title());
        courseToUpdate.setDescription(requestDto.description());
        Course updatedCourse = courseRepository.save(courseToUpdate);
        log.info("Ccurso con id: {} actualizado satisfactoriamente", id);
        return courseMapper.mapToCourseDto(updatedCourse);
    }

    @Override
    @Transactional
    public void deleteCourse(Long id) {
        boolean existsEnrollmets = enrollmentRepository.existsByCourseId(id);
        if (existsEnrollmets){
            log.info("Error al eliminar el curso con Id: {} - tiene estudiantes inscritos", id);
            throw new DuplicatedResourceException(ApiConstants.VALIDATE_ERR_DATA_INTEGRITY);
        }

        log.info("Ejecutando la eliminacion del curso con id: {}", id);
        Course courseToDelete = courseRepository.findById(id)
                .orElseThrow(()-> new CourseIdNotFoundException(ApiConstants.BUSINESS_ERR_ID_NOT_FOUND));
        courseRepository.delete(courseToDelete);
    }
}
