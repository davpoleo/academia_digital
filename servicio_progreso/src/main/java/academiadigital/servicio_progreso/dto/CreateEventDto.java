package academiadigital.servicio_progreso.dto;

import java.time.LocalDateTime;

public record CreateEventDto(
        Long courseId,
        Long studentId,
        String eventType,
        String value
) {
}
