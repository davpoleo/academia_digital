package academiadigital.servicio_estudiante.controller;

import academiadigital.servicio_estudiante.dto.StudentRequestDto;
import academiadigital.servicio_estudiante.dto.StudentResponseDto;
import academiadigital.servicio_estudiante.service.StudentService;
import academiadigital.servicio_estudiante.util.ApiConstants;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.CONTROLLER_MAP_BASE)
@Data
public class StudentController {
    private final StudentService studentService;

    @GetMapping(ApiConstants.CONTROLLER_MAP_GET_BY_ID)
    public ResponseEntity<StudentResponseDto> getStudentById(@PathVariable Long id){
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @GetMapping(ApiConstants.CONTROLLER_MAP_GET_BY_EMAIL)
    public ResponseEntity<StudentResponseDto> getStudentByEmail(@PathVariable String email){
        return ResponseEntity.ok(studentService.getStudentByEmail(email));
    }

    @GetMapping(ApiConstants.CONTROLLER_MAP_GET_ALL)
    public ResponseEntity<List<StudentResponseDto>> getAllStudents(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @PostMapping(ApiConstants.CONTROLLER_MAP_CREATE)
    public ResponseEntity<StudentResponseDto> createStudent(
            @Valid @RequestBody StudentRequestDto request
            ){
        StudentResponseDto createdStudent = studentService.createStudent(request);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    @PutMapping(ApiConstants.CONTROLLER_MAP_UPDATE)
    public ResponseEntity<StudentResponseDto> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentRequestDto request
    ){
        StudentResponseDto updatedStudent = studentService.updateStudent(id, request);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping(ApiConstants.CONTROLLER_MAP_DELETE)
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}