package academiadigital.servicio_curso.service;

import academiadigital.servicio_curso.dto.CourseRequestDto;
import academiadigital.servicio_curso.dto.CourseResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {
    //CRUD
    CourseResponseDto getCourseById(Long id);
    CourseResponseDto getCourseByTitle(String title);
    List<CourseResponseDto> getAllCourses();
    CourseResponseDto createCourse(CourseRequestDto requestDto);
    CourseResponseDto updateCourse(Long id, CourseRequestDto requestDto);
    void deleteCourse(Long id);
}
