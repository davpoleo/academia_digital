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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    @Transactional(readOnly = true)
    public StudentResponseDto getStudentById(Long id) {
        log.info("Realizando busqueda de estudiante por id: {}", id);
        Student searchStudentbyId = studentRepository.findById(id)
                .orElseThrow(()-> new StudentIdNotFoundException(id, ApiConstants.BUSINESS_ERR_ID_NOT_FOUND));
        return studentMapper.mapToStudentDto(searchStudentbyId);
    }

    @Override
    @Transactional(readOnly = true)
    public StudentResponseDto getStudentByEmail(String email) {
        log.info("Realizando busqueda de estudiante por email: {}", email);
        Student searchStudentByEmail = studentRepository.findByEmail(email)
                .orElseThrow(()-> new StudentEmailNotFoundException(email, ApiConstants.BUSINESS_ERR_EMAIL_NOT_FOUND));
        return studentMapper.mapToStudentDto(searchStudentByEmail);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentResponseDto> getAllStudents() {
        log.info("Realizando busqueda de todos los estudiantes registrados");
        return studentRepository.findAll().stream()
                .map(studentMapper::mapToStudentDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public StudentResponseDto createStudent(StudentRequestDto request) {
        log.info("Iniciando la creacion del estudiante con email: {}", request.email());
        studentRepository.findByEmail(request.email()).ifPresent(student -> {
            throw new DuplicatedResourceException(request.email(), ApiConstants.BUSINESS_ERR_DUPLICATED_EMAIL);
        });
        Student student = new Student();
        student.setName(request.name());
        student.setLastName(request.lastName());
        student.setEmail(request.email());
        student.setCreationDate(LocalDateTime.now());
        Student newStudent = studentRepository.save(student);
        log.info("Estudiante con email: {} creado satisfactoriamente", request.email());
        return studentMapper.mapToStudentDto(newStudent);
    }

    @Override
    public StudentResponseDto updateStudent(Long id, StudentRequestDto request) {
        log.info("Iniciando la Actualizacion del estudiante con id: {}", id);
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
         log.info("Estudiante con id: {} creado satisfactoriamente", id);
         return studentMapper.mapToStudentDto(updatedStudent);
    }

    @Override
    @Transactional
    public void deleteStudent(Long id) {
        log.info("Ejecutando la eliminacion del estudiante con id: {}", id);
        Student studentToDelete = studentRepository.findById(id)
                .orElseThrow(()-> new StudentIdNotFoundException(id, ApiConstants.BUSINESS_ERR_ID_NOT_FOUND));
        studentRepository.delete(studentToDelete);
    }
}
