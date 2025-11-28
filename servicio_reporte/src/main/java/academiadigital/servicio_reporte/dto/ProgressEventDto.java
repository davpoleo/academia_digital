package academiadigital.servicio_reporte.dto;

import java.time.LocalDateTime;

public record ProgressEventDto (
        Long courseId,
        Long studentId,
        String eventType,
        String value,
        LocalDateTime timestamp
){
}
