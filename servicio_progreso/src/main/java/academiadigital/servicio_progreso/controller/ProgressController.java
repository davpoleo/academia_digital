package academiadigital.servicio_progreso.controller;

import academiadigital.servicio_progreso.dto.ProgressRequestDto;
import academiadigital.servicio_progreso.dto.ProgressResponseDto;
import academiadigital.servicio_progreso.service.ProgressService;
import academiadigital.servicio_progreso.util.ApiConstants;
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
@Tag(name = "Progresos", description = "Operaciones CRUD para la gestion de progresos academicos")
public class ProgressController {

    private final ProgressService progressService;

    @Operation(summary = "Obtiene informacion de progreso academico por ID del estudiante")
    @ApiResponse(responseCode = "200", description = "Progreso de estudiante encontrado por id")
    @ApiResponse(responseCode = "404", description = "Progreso de estudiante encontrado por id")
    @GetMapping(ApiConstants.CONTROLLER_MAP_GET_BY_ID)
    public ResponseEntity<ProgressResponseDto> getProgressByStudentId(@PathVariable Long id){
        return ResponseEntity.ok(progressService.getProgressByStudentId(id));
    }

    @Operation(summary = "Obtiene informacion de todos los cursos")
    @ApiResponse(responseCode = "200", description = "Retorna una lista de los cursos")
    @GetMapping(ApiConstants.CONTROLLER_MAP_GET_ALL)
    public ResponseEntity<List<ProgressResponseDto>> getAllProgress(){
        return ResponseEntity.ok(progressService.getAllProgress());
    }

    @Operation(summary = "Crea un nuevo  o actualiza un progreso academico")
    @ApiResponse(responseCode = "201", description = "Progreso creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos de entrada invalidos")
    @PostMapping(ApiConstants.CONTROLLER_MAP_CREATE)
    public ResponseEntity<ProgressResponseDto> CreateOrUpdateProgress(
            @Valid @RequestBody ProgressRequestDto requestDto
    ){
        ProgressResponseDto progressCreated = progressService.CreateOrUpdateProgress(requestDto);
        return new ResponseEntity<>(progressCreated, HttpStatus.CREATED);
    }

    @Operation(summary = "Elimina una progreso academcio por Id de curso y estudiante")
    @ApiResponse(responseCode = "204", description = "Estudiante eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Id para eliminacion no encontrado")
    @DeleteMapping(ApiConstants.CONTROLLER_MAP_ENROLLMENT_DELETE)
    public ResponseEntity<Void> deleteEnrollment(@PathVariable Long courseId, @PathVariable Long studentId){
        progressService.deleteProgress(studentId, courseId);
        return ResponseEntity.noContent().build();
    }
}
