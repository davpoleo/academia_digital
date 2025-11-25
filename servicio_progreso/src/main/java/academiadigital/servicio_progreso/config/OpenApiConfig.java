package academiadigital.servicio_progreso.config;

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
                        .title("Api de Progresso academico")
                        .version("1.0.0")
                        .description("Microservicio para gestionar la creacion de progreso de los estudiantes en los cursos")
                        .contact( new Contact()
                                .name("David Poleo")
                                .email("david@email.com")
                                .url("https://davpoleo.com")
                        )
                );
    }
}
