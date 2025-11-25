package academiadigital.servicio_curso.client;


import academiadigital.servicio_curso.dto.StudentResponseDto;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static academiadigital.servicio_curso.util.ApiConstants.*;

@FeignClient(
        name = FEING_V1_ESTUDIANTES_NAME,
        url = FEING_V1_ESTUDIANTES_URL,
        path = FEING_V1_ESTUDIANTES_PATH
)
public interface StudentFeingClient {
    @GetMapping(CONTROLLER_MAP_GET_BY_ID)
    StudentResponseDto getStudenById(@Valid @PathVariable("id") Long id);
}

