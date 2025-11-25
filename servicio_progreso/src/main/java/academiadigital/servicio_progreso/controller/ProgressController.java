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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstants.CONTROLLER_MAP_BASE)
@Data
@Tag(name = "Progresos", description = "Operaciones CRUD para la gestion de progresos academicos")
public class ProgressController {

    private final ProgressService progressService;

    @Operation(summary = "Crea un nuevo progreso academico")
    @ApiResponse(responseCode = "201", description = "Progreso creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos de entrada invalidos")
    @PostMapping(ApiConstants.CONTROLLER_MAP_CREATE)
    public ResponseEntity<ProgressResponseDto> createProgress(
            @Valid @RequestBody ProgressRequestDto requestDto
    ){
        ProgressResponseDto progressCreated = progressService.CreateOrUpdateProgress(requestDto);
        return new ResponseEntity<>(progressCreated, HttpStatus.CREATED);
    }
}
