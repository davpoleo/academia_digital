package academiadigital.servicio_curso.dto;

public record EnrollmentResponseDto(
        Long id,
        Long courseId,
        Long studentId
) {
}
