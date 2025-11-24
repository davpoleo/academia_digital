package academiadigital.servicio_curso.mapper;

import academiadigital.servicio_curso.dto.CourseResponseDto;
import academiadigital.servicio_curso.model.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {
    public CourseResponseDto mapToCourseDto(Course course){
        return new CourseResponseDto(
                course.getId(),
                course.getTitle(),
                course.getDescription()
        );
    }
}
