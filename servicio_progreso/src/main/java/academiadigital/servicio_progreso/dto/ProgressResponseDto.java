package academiadigital.servicio_progreso.dto;

import java.time.LocalDateTime;

public record ProgressResponseDto(
        Long id,
        Long studentId,
        Long courseId,
        Integer progressPercentage,
        LocalDateTime updateDate
) {
}
