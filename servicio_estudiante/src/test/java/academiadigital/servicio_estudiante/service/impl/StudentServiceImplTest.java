package academiadigital.servicio_estudiante.service.impl;

import academiadigital.servicio_estudiante.dto.StudentResponseDto;
import academiadigital.servicio_estudiante.exception.StudentIdNotFoundException;
import academiadigital.servicio_estudiante.mapper.StudentMapper;
import academiadigital.servicio_estudiante.model.Student;
import academiadigital.servicio_estudiante.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceImplTest {
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private StudentMapper studentMapper;
    @InjectMocks
    private StudentServiceImpl studentService;

    @Test
    void studentFoundById(){
        Long studentId = 1L;
        Student mockStudent = new Student(
                studentId,
                "Maria",
                "Magdalena",
                "maria@test.com",
                null);
        StudentResponseDto expectedDto = new StudentResponseDto(
                studentId,
                "Maria",
                "Magdalena",
                "maria@test.com",
                null);

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(mockStudent));
        when(studentMapper.mapToStudentDto(mockStudent)).thenReturn(expectedDto);
        StudentResponseDto actualDto = studentService.getStudentById(studentId);

        assertNotNull(actualDto, "El DTO no debe ser nulo");
        assertEquals(expectedDto.email(), actualDto.email(), "El email debe coincidir");

        verify(studentRepository, times(1)).findById(studentId);
        verify(studentMapper, times(1)).mapToStudentDto(mockStudent);
    }

    @Test
    void studentNotFoundById(){
        Long invalidId = 99L;

        when(studentRepository.findById(invalidId)).thenReturn(Optional.empty());

        assertThrows(StudentIdNotFoundException.class, () -> {
            studentService.getStudentById(invalidId);
        });

        verify(studentRepository, times(1)).findById(invalidId);
        verify(studentMapper, never()).mapToStudentDto(any());

    }



}
