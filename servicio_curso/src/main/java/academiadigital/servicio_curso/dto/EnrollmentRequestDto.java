package academiadigital.servicio_curso.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Informacion requerida para crear o actualizar un curso")
public record EnrollmentRequestDto(
        @NotNull(message = "El ID del curso es obligatorio")
        Long courseId,
        @NotNull(message = "El ID del estudiante es obligatorio")
        Long studentId
) {
}
