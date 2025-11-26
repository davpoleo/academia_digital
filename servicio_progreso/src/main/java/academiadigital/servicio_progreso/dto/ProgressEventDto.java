package academiadigital.servicio_progreso.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record ProgressEventDto(
        Long id,
        Long courseId,
        Long studentId,
        String eventType,
        String value,
        LocalDateTime timestamp
) {
}
