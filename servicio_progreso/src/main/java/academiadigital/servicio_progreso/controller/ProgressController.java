package academiadigital.servicio_progreso.controller;

import academiadigital.servicio_progreso.dto.CreateEventDto;
import academiadigital.servicio_progreso.dto.ProgressEventDto;
import academiadigital.servicio_progreso.service.ProgressEventService;
import academiadigital.servicio_progreso.util.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.CONTROLLER_MAP_BASE)
@Data
@Tag(name = "Progresos", description = "Obtiene informacion de los eventos capturados en el sistema")
public class ProgressController {

    private final ProgressEventService progressEventService;

    @Operation(summary = "Obtiene informacion de progreso academico por ID del curso")
    @ApiResponse(responseCode = "200", description = "Obtiene eventos por id del curso")
    @ApiResponse(responseCode = "404", description = "Eventos por id del curso no encontrados")
    @GetMapping(ApiConstants.CONTROLLER_MAP_GET_BY_ID)
    public ResponseEntity<List<ProgressEventDto>> getRawEventByCourseId(@PathVariable Long courseId){
        return ResponseEntity.ok(progressEventService.getRawEventByCourseId(courseId));
    }

    @Operation(summary = "Crea un nuevo evento")
    @ApiResponse(responseCode = "201", description = "Evento creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos de entrada invalidos")
    //@PostMapping(ApiConstants.CONTROLLER_MAP_CREATE)
    @PostMapping
    public ResponseEntity<ProgressEventDto> createStudent(
            @Valid @RequestBody CreateEventDto request
    ){
        ProgressEventDto createEvent = progressEventService.createEvent(request);
        return new ResponseEntity<>(createEvent, HttpStatus.CREATED);
    }
}
