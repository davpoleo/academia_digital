package academiadigital.servicio_reporte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ServicioReporteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioReporteApplication.class, args);
	}

}
