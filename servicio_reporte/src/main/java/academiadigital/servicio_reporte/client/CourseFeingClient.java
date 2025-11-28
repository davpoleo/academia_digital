package academiadigital.servicio_reporte.client;

import academiadigital.servicio_reporte.dto.CourseResponseDto;
import academiadigital.servicio_reporte.util.ApiConstants;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = ApiConstants.FEING_CURSOS_NAME,
        url = ApiConstants.FEING_CURSOS_URL,
        path = ApiConstants.FEING_CURSOS_PATH
)
public interface CourseFeingClient {
    @GetMapping(ApiConstants.FEING_CURSOS_MAPPING_COURSE_ID)
    Optional<CourseResponseDto> getCourseInformationById(@Valid @PathVariable("courseId") Long courseId);

    @GetMapping(ApiConstants.FEING_CURSOS_MAPPING_STUDENTS_ID_BY_COURSE)
    Optional<CourseResponseDto> getStudentsByCourseId(@Valid @PathVariable("courseId") Long courseId);
}
