package academiadigital.servicio_estudiante.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "DTO de respuesta al cliente para la informacion de un estudiante")
public record StudentResponseDto(
        @Schema(description = "Id del estudiante", example = "101")
        Long id,

        @Schema(description = "Nombre del estudiante", example = "David")
        String name,

        @Schema(description = "Apellido del estudiante", example = "Poleo")
        String lastName,

        @Schema(description = "email del estudiante", example = "email@example.com")
        String email,

        @Schema(description = "Fecha de creacion", example = "2025-11-24T16:08:25.593")
        LocalDateTime creationDate
) {
}
