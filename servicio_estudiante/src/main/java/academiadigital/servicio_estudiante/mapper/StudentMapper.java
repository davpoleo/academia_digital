package academiadigital.servicio_estudiante.mapper;

import academiadigital.servicio_estudiante.dto.StudentResponseDto;
import academiadigital.servicio_estudiante.model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    public StudentResponseDto mapToStudentDto(Student student){
        return new StudentResponseDto(
                student.getId(),
                student.getName(),
                student.getLastName(),
                student.getEmail(),
                student.getCreationDate()
        );
    }
}
