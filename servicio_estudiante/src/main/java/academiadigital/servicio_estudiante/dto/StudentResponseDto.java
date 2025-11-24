package academiadigital.servicio_estudiante.dto;

import java.time.LocalDateTime;

public record StudentResponseDto(
        Long id,
        String name,
        String lastName,
        String email,
        LocalDateTime creationDate
) {
}
