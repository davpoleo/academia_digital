package academiadigital.servicio_reporte.dto;

public record StudentMetricsDto (
        Long studentId,
        String name,
        String email,
        Double averageGrades,
        Long totalLessonsViewed,
        Long forumParticipation,
        Long daysSinceLastLogin,
        String performanceStatus
){
}
