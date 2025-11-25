package academiadigital.servicio_curso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ServicioCursoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioCursoApplication.class, args);
	}

}
