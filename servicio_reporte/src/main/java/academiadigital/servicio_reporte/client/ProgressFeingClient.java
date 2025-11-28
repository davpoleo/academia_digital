package academiadigital.servicio_reporte.client;

import academiadigital.servicio_reporte.dto.CourseResponseDto;
import academiadigital.servicio_reporte.dto.ProgressResponseDto;
import academiadigital.servicio_reporte.util.ApiConstants;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = ApiConstants.FEING_PROGRESO_NAME,
        url = ApiConstants.FEING_PROGRESO_URL,
        path = ApiConstants.FEING_PROGRESO_PATH
)
public interface ProgressFeingClient {
    @GetMapping(ApiConstants.FEING_PROGRESO_MAPPING_RAW_EVENTS_BY_COURSE)
    Optional<ProgressResponseDto> getRawEventsByCourse(@Valid @PathVariable("courseId") Long courseId);
}
