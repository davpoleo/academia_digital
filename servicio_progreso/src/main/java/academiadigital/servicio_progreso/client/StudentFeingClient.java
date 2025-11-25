package academiadigital.servicio_progreso.client;

import academiadigital.servicio_progreso.dto.StudentResponseDto;
import academiadigital.servicio_progreso.util.ApiConstants;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = ApiConstants.FEING_V1_ESTUDIANTES_NAME,
        url = ApiConstants.FEING_V1_ESTUDIANTES_URL,
        path = ApiConstants.FEING_V1_ESTUDIANTES_PATH
)
public interface StudentFeingClient {
    @GetMapping(ApiConstants.CONTROLLER_MAP_GET_BY_ID)
    StudentResponseDto getStudenById(@Valid @PathVariable("id") Long id);
}
