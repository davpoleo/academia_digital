package academiadigital.servicio_curso.controller;

import academiadigital.servicio_curso.dto.CourseRequestDto;
import academiadigital.servicio_curso.dto.CourseResponseDto;
import academiadigital.servicio_curso.service.CourseService;
import academiadigital.servicio_curso.util.ApiConstants;
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
@Tag(name = "Cursos", description = "Operaciones CRUD para la gestion de cursos")
public class CourseController {
    private final CourseService courseService;

    @Operation(summary = "Obtiene informacion de un curso por su ID")
    @ApiResponse(responseCode = "200", description = "Curso encontrado por id")
    @ApiResponse(responseCode = "404", description = "Curso no encontrado por id")
    @GetMapping(ApiConstants.CONTROLLER_MAP_GET_BY_ID)
    public ResponseEntity<CourseResponseDto> getCourseById(@PathVariable Long id){
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @Operation(summary = "Obtiene informacion de un curso por su titulo")
    @ApiResponse(responseCode = "200", description = "Curso encontrado por titulo")
    @ApiResponse(responseCode = "404", description = "Curso no encontrado por titulo")
    @GetMapping(ApiConstants.CONTROLLER_MAP_GET_BY_TITLE)
    public ResponseEntity<CourseResponseDto> getStudentByEmail(@PathVariable String title){
        return ResponseEntity.ok(courseService.getCourseByTitle(title));
    }

    @Operation(summary = "Obtiene informacion de todos los cursos")
    @ApiResponse(responseCode = "200", description = "Retorna una lista de los cursos")
    @GetMapping(ApiConstants.CONTROLLER_MAP_GET_ALL)
    public ResponseEntity<List<CourseResponseDto>> getAllStudents(){
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @Operation(summary = "Crea un nuevo curso")
    @ApiResponse(responseCode = "201", description = "curso creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos de entrada invalidos")
    @PostMapping(ApiConstants.CONTROLLER_MAP_CREATE)
    public ResponseEntity<CourseResponseDto> createStudent(
            @Valid @RequestBody CourseRequestDto request
    ){
        CourseResponseDto createdCourse = courseService.createCourse(request);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualiza un curso")
    @ApiResponse(responseCode = "200", description = "curso actualizado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos de entrada invalidos o Id no valido")
    @ApiResponse(responseCode = "404", description = "Id de actualizacion no encontrado")
    @PutMapping(ApiConstants.CONTROLLER_MAP_UPDATE)
    public ResponseEntity<CourseResponseDto> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody CourseRequestDto request
    ){
        CourseResponseDto updatedStudent = courseService.updateCourse(id, request);
        return ResponseEntity.ok(updatedStudent);
    }

    @Operation(summary = "Elimina un curso por Id")
    @ApiResponse(responseCode = "204", description = "Curso eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Id para eliminacion no encontrado")
    @DeleteMapping(ApiConstants.CONTROLLER_MAP_DELETE)
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id){
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}