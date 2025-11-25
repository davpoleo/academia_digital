package academiadigital.servicio_progreso.dto;

import jakarta.validation.constraints.NotNull;

public record StudentResponseDto(
        Long id,
        @NotNull(message = "El nombre es obligatorio.")
        String name,
        @NotNull(message = "El apellido es obligatorio.")
        String LastName,
        @NotNull(message = "El email es obligatorio.")
        String email
) {
}
