package academiadigital.servicio_curso.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;

public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenApiConfig(){
        return new OpenAPI()
                .info(new Info()
                        .title("Api de Cursos e inscripciones")
                        .version("1.0.0")
                        .description("Microservicio para gestionar la creacion de cursos e inscripciones en el sistema")
                        .contact( new Contact()
                                .name("David Poleo")
                                .email("david@email.com")
                                .url("https://davpoleo.com")
                        )
                );
    }
}
