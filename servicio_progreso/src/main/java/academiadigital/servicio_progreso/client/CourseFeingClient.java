package academiadigital.servicio_progreso.client;

import academiadigital.servicio_progreso.dto.CourseResponseDto;
import academiadigital.servicio_progreso.dto.StudentResponseDto;
import academiadigital.servicio_progreso.util.ApiConstants;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = ApiConstants.FEING_V1_COURSES_NAME,
        url = ApiConstants.FEING_V1_COURSES_URL,
        path = ApiConstants.FEING_V1_COURSES_PATH
)
public interface CourseFeingClient {
    @GetMapping(ApiConstants.CONTROLLER_MAP_GET_BY_ID)
    CourseResponseDto getCourseById(@Valid @PathVariable("id") Long id);
}
