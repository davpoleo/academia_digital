package academiadigital.servicio_progreso.service.impl;

import academiadigital.servicio_progreso.dto.CreateEventDto;
import academiadigital.servicio_progreso.dto.ProgressEventDto;
import academiadigital.servicio_progreso.mapper.ProgressEventMapper;
import academiadigital.servicio_progreso.model.ProgressEvent;
import academiadigital.servicio_progreso.repository.ProgressEventRespository;
import academiadigital.servicio_progreso.service.ProgressEventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class ProgressEventServiceImpl implements ProgressEventService {
    private final ProgressEventRespository progressEventRespository;
    private final ProgressEventMapper progressEventMapper;


    @Override
    @Transactional(readOnly = true)
    public List<ProgressEventDto> getRawEventByCourseId(Long courseId) {
        List<ProgressEvent> events = progressEventRespository.findByCourseId(courseId);
        return events.stream()
                .map(progressEventMapper::mapToProgressEventDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProgressEventDto createEvent(CreateEventDto requestDto) {
        ProgressEvent progressEvent = new ProgressEvent();
        progressEvent.setCourseId(requestDto.courseId());
        progressEvent.setStudentId(requestDto.studentId());
        progressEvent.setEventType(requestDto.eventType());
        progressEvent.setValue(requestDto.value());
        progressEvent.setTimestamp(LocalDateTime.now());
        ProgressEvent newProgressEvent = progressEventRespository.save(progressEvent);
        return progressEventMapper.mapToProgressEventDto(newProgressEvent);
    }
}

// CON FEING SE PODRIA CONSULTAR LA EXISTENCIA DE LOS ID A REGISTRAR
//try {
//        log.info("Consultando existencia de curso con ID: {}", requestDto.courseId());
//CourseResponseDto courseResponseDto = courseFeingClient.getCourseById(requestDto.courseId());
//            log.info("Curso con ID: {} encontrado", requestDto.courseId());
//        } catch (FeignException.NotFound error) {
//        log.info("Curso con ID: {} no existe", requestDto.courseId());
//        throw new ResourceNotFoundException(ApiConstants.BUSINESS_ERR_ID_NOT_FOUND);
//        }
//
//                try {
//                log.info("Consultando existencia de estudiante con ID: {}", requestDto.studentId());
//StudentResponseDto studentResponseDto = studentFeingClient.getStudenById(requestDto.studentId());
//            log.info("Estudiante con ID: {} encontrado", requestDto.studentId());
//        } catch (Exception e) {
//        log.info("Estudiante con ID: {} no existe", requestDto.studentId());
//        throw new ResourceNotFoundException(ApiConstants.BUSINESS_ERR_ID_NOT_FOUND);
//        }

