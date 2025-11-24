package academiadigital.servicio_estudiante.service;

import academiadigital.servicio_estudiante.dto.StudentRequestDto;
import academiadigital.servicio_estudiante.dto.StudentResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {

    //CRUD
    StudentResponseDto getStudentById(Long id);
    StudentResponseDto getStudentByEmail(String email);
    List<StudentResponseDto> getAllStudents();
    StudentResponseDto createStudent(StudentRequestDto request);
    StudentResponseDto updateStudent(Long id, StudentRequestDto request);
    void deleteStudent(Long id);



}
