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
                        .title("Api de Progreso")
                        .version("1.0.0")
                        .description("Microservicio para capturar todos los eventos realizados")
                        .contact( new Contact()
                                .name("David Poleo")
                                .email("david@email.com")
                                .url("https://davpoleo.com")
                        )
                );
    }
}
