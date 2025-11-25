package academiadigital.servicio_curso.mapper;

import academiadigital.servicio_curso.dto.EnrollmentResponseDto;
import academiadigital.servicio_curso.model.Enrollment;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentMapper {
    public EnrollmentResponseDto mapToEnrollmentDto(Enrollment enrollment){
        return new EnrollmentResponseDto(
                enrollment.getId(),
                enrollment.getCourseId(),
                enrollment.getCourseId()
        );
    }
}
