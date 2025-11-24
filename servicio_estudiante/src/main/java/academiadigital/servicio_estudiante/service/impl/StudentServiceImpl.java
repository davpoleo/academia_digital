package academiadigital.servicio_estudiante.service.impl;

import academiadigital.servicio_estudiante.dto.StudentRequestDto;
import academiadigital.servicio_estudiante.dto.StudentResponseDto;
import academiadigital.servicio_estudiante.exception.DuplicatedResourceException;
import academiadigital.servicio_estudiante.exception.StudentEmailNotFoundException;
import academiadigital.servicio_estudiante.exception.StudentIdNotFoundException;
import academiadigital.servicio_estudiante.mapper.StudentMapper;
import academiadigital.servicio_estudiante.model.Student;
import academiadigital.servicio_estudiante.repository.StudentRepository;
import academiadigital.servicio_estudiante.service.StudentService;
import academiadigital.servicio_estudiante.util.ApiConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;


    @Override
    @Transactional(readOnly = true)
    public StudentResponseDto getStudentById(Long id) {
        Student searchStudentbyId = studentRepository.findById(id)
                .orElseThrow(()-> new StudentIdNotFoundException(id, ApiConstants.BUSINESS_ERR_ID_NOT_FOUND));
        return studentMapper.mapToStudentDto(searchStudentbyId);
    }

    @Override
    @Transactional(readOnly = true)
    public StudentResponseDto getStudentByEmail(String email) {
        Student searchStudentByEmail = studentRepository.findByEmail(email)
                .orElseThrow(()-> new StudentEmailNotFoundException(email, ApiConstants.BUSINESS_ERR_EMAIL_NOT_FOUND));
        return studentMapper.mapToStudentDto(searchStudentByEmail);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentResponseDto> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(studentMapper::mapToStudentDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public StudentResponseDto createStudent(StudentRequestDto request) {
        studentRepository.findByEmail(request.email()).ifPresent(student -> {
            throw new DuplicatedResourceException(request.email(), ApiConstants.BUSINESS_ERR_DUPLICATED_EMAIL);
        });
        Student student = new Student();
        student.setName(request.name());
        student.setLastName(request.lastName());
        student.setEmail(request.email());
        student.setCreationDate(LocalDateTime.now());
        Student newStudent = studentRepository.save(student);
        return studentMapper.mapToStudentDto(newStudent);
    }

    @Override
    public StudentResponseDto updateStudent(Long id, StudentRequestDto request) {
         Student studentToUpdate = studentRepository.findById(id).
                 orElseThrow(()-> new StudentIdNotFoundException(id, ApiConstants.BUSINESS_ERR_ID_NOT_FOUND));

         studentRepository.findByEmail(request.email()).ifPresent(student -> {
             if (!student.getId().equals(id)){
                 throw new DuplicatedResourceException(request.email(), ApiConstants.BUSINESS_ERR_DUPLICATED_EMAIL);
             }
         });
         studentToUpdate.setName(request.name());
         studentToUpdate.setLastName(request.lastName());
         studentToUpdate.setEmail(request.email());
         Student updatedStudent = studentRepository.save(studentToUpdate);
         return studentMapper.mapToStudentDto(updatedStudent);
    }

    @Override
    @Transactional
    public void deleteStudent(Long id) {
        Student studentToDelete = studentRepository.findById(id)
                .orElseThrow(()-> new StudentIdNotFoundException(id, ApiConstants.BUSINESS_ERR_ID_NOT_FOUND));
        studentRepository.delete(studentToDelete);
    }
}
