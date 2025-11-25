package academiadigital.servicio_progreso.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Schema(description = "DTO de entrada para la informacion de progreso academico")
public record ProgressRequestDto(
        @NotNull(message = "El ID del estudiante es obligatorio")
        Long studentId,
        @NotNull(message = "El ID del curso es obligatorio")
        Long courseId,
        @NotNull(message = "se debe establecer un porcentaje")
        @Min(value = 0, message = "El porcentaje no puede ser menor a 0")
        @Max(value = 100, message = "El porcentaje no puede ser mayor a 100")
        Integer progressPercentage
) {
}
