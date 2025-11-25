package academiadigital.servicio_curso.dto;

import jakarta.validation.constraints.NotNull;

public record CourseRequestDto(
//        @NotNull(message = "El id es obligatorio")
//        Long id,
        @NotNull(message = "El titulo del curso es obligatorio")
        String title,
        String description
) {
}
