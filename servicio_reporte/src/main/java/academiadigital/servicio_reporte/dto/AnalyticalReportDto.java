package academiadigital.servicio_reporte.dto;

import java.util.List;

public record AnalyticalReportDto (
        Long courseId,
        String courseTitle,
        List<StudentMetricsDto> students
){
}
