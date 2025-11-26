package academiadigital.servicio_progreso.mapper;

import academiadigital.servicio_progreso.dto.ProgressEventDto;
import academiadigital.servicio_progreso.model.ProgressEvent;
import org.springframework.stereotype.Component;

@Component
public class ProgressEventMapper {
    public ProgressEventDto mapToProgressEventDto(ProgressEvent progressEvent){
        return new ProgressEventDto(
                progressEvent.getId(),
                progressEvent.getCourseId(),
                progressEvent.getCourseId(),
                progressEvent.getEventType(),
                progressEvent.getValue(),
                progressEvent.getTimestamp()
        );
    }
}
