package academiadigital.servicio_estudiante.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApiConfig(){
        return new OpenAPI()
                .info(new Info()
                        .title("Api de Estudiantes")
                        .version("1.0.0")
                        .description("Microservicio para gestionar los estudiantes registrados en el sistema")
                        .contact( new Contact()
                                .name("David Poleo")
                                .email("david@email.com")
                                .url("https://davpoleo.com")
                        )
                );
    }
}
