package academiadigital.servicio_progreso.service;

import academiadigital.servicio_progreso.dto.CreateEventDto;
import academiadigital.servicio_progreso.dto.ProgressEventDto;

import java.util.List;

public interface ProgressEventService {
    List<ProgressEventDto> getRawEventByCourseId(Long courseId);
    ProgressEventDto createEvent(CreateEventDto requestDto);
}
