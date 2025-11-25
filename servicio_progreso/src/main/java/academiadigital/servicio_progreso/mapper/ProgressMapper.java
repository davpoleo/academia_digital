package academiadigital.servicio_progreso.mapper;

import academiadigital.servicio_progreso.dto.ProgressResponseDto;
import academiadigital.servicio_progreso.model.Progress;
import org.springframework.stereotype.Component;

@Component
public class ProgressMapper {
    public ProgressResponseDto mapToProgressDto(Progress progress){
        return new ProgressResponseDto(
                progress.getId(),
                progress.getCourseId(),
                progress.getCourseId(),
                progress.getProgressPercentage(),
                progress.getUpdateDate()
        );
    }
}
