package academiadigital.servicio_estudiante.dto;

import academiadigital.servicio_estudiante.util.ApiConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record StudentRequestDto(
        @NotBlank(message = ApiConstants.VALIDATE_ERR_BLANKS_MESSAGE)
        String name,
        @NotBlank(message = ApiConstants.VALIDATE_ERR_BLANKS_MESSAGE)
        String lastName,
        @NotBlank(message = ApiConstants.VALIDATE_ERR_BLANKS_MESSAGE)
        @Email(message = ApiConstants.VALIDATE_ERR_EMAIL_MESSAGE)
        String email
) {
}
