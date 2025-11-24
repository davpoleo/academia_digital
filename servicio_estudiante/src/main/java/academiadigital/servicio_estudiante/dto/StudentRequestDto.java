package academiadigital.servicio_estudiante.dto;

import academiadigital.servicio_estudiante.util.ApiConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "DTO de entrada para la informacion de un estudiante")
public record StudentRequestDto(
        @NotBlank(message = ApiConstants.VALIDATE_ERR_BLANKS_MESSAGE)
        @Schema(description = "Nombre del estudiante", example = "David")
        String name,
        @NotBlank(message = ApiConstants.VALIDATE_ERR_BLANKS_MESSAGE)
        @Schema(description = "Apellido del estudiante", example = "Poleo")
        String lastName,
        @NotBlank(message = ApiConstants.VALIDATE_ERR_BLANKS_MESSAGE)
        @Email(message = ApiConstants.VALIDATE_ERR_EMAIL_MESSAGE)
        @Schema(description = "email del estudiante", example = "email@example.com")
        String email
) {
}
