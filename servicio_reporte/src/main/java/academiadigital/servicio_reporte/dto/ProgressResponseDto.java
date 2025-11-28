package academiadigital.servicio_reporte.dto;

import java.time.LocalDateTime;


public record ProgressResponseDto(
        Long id,
        Long courseId,
        Long studentId,
        String eventType,
        String value,
        LocalDateTime timestamp
) {
}
