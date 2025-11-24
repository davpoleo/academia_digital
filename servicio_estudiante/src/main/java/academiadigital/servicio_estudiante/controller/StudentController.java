package academiadigital.servicio_estudiante.controller;

import academiadigital.servicio_estudiante.dto.StudentRequestDto;
import academiadigital.servicio_estudiante.dto.StudentResponseDto;
import academiadigital.servicio_estudiante.service.StudentService;
import academiadigital.servicio_estudiante.util.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.CONTROLLER_MAP_BASE)
@Data
@Tag(name = "Estudiantes", description = "Operaciones CRUD para la gestion de estudiantes")
public class StudentController {
    private final StudentService studentService;

    @Operation(summary = "Obtiene informacion de un estudiante por su ID")
    @ApiResponse(responseCode = "200", description = "Estudiante encontrado por id")
    @ApiResponse(responseCode = "404", description = "Estudiante no encontrado por id")
    @GetMapping(ApiConstants.CONTROLLER_MAP_GET_BY_ID)
    public ResponseEntity<StudentResponseDto> getStudentById(@PathVariable Long id){
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @Operation(summary = "Obtiene informacion de un estudiante por su email")
    @ApiResponse(responseCode = "200", description = "Estudiante encontrado por email")
    @ApiResponse(responseCode = "404", description = "Estudiante no encontrado por email")
    @GetMapping(ApiConstants.CONTROLLER_MAP_GET_BY_EMAIL)
    public ResponseEntity<StudentResponseDto> getStudentByEmail(@PathVariable String email){
        return ResponseEntity.ok(studentService.getStudentByEmail(email));
    }

    @Operation(summary = "Obtiene informacion de todos los estudiantes")
    @ApiResponse(responseCode = "200", description = "Retorna una lista de estudiantes")
    @GetMapping(ApiConstants.CONTROLLER_MAP_GET_ALL)
    public ResponseEntity<List<StudentResponseDto>> getAllStudents(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @Operation(summary = "Crea un nuevo estudiante")
    @ApiResponse(responseCode = "201", description = "Estudiante creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos de entrada invalidos")
    @PostMapping(ApiConstants.CONTROLLER_MAP_CREATE)
    public ResponseEntity<StudentResponseDto> createStudent(
            @Valid @RequestBody StudentRequestDto request
            ){
        StudentResponseDto createdStudent = studentService.createStudent(request);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualiza un estudiante")
    @ApiResponse(responseCode = "200", description = "Estudiante actualizado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos de entrada invalidos o Id no valido")
    @ApiResponse(responseCode = "404", description = "Id de actualizacion no encontrado")
    @PutMapping(ApiConstants.CONTROLLER_MAP_UPDATE)
    public ResponseEntity<StudentResponseDto> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentRequestDto request
    ){
        StudentResponseDto updatedStudent = studentService.updateStudent(id, request);
        return ResponseEntity.ok(updatedStudent);
    }

    @Operation(summary = "Elimina un estudiante por Id")
    @ApiResponse(responseCode = "204", description = "Estudiante eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Id para eliminacion no encontrado")
    @DeleteMapping(ApiConstants.CONTROLLER_MAP_DELETE)
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}