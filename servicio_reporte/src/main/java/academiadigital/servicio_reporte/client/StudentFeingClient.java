package academiadigital.servicio_reporte.client;

import academiadigital.servicio_reporte.util.ApiConstants;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name = ApiConstants.FEING_ESTUDIANTES_NAME,
        url = ApiConstants.FEING_ESTUDIANTES_URL,
        path = ApiConstants.FEING_ESTUDIANTES_PATH
)
public interface StudentFeingClient {
}
