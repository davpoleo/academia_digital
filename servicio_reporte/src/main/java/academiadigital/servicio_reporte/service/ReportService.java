package academiadigital.servicio_reporte.service;

import academiadigital.servicio_reporte.dto.AnalyticalReportDto;
import academiadigital.servicio_reporte.dto.ProgressEventDto;
import academiadigital.servicio_reporte.dto.ProgressResponseDto;
import academiadigital.servicio_reporte.dto.StudentMetricsDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReportService {
    AnalyticalReportDto generateAnalyticalReport(Long courseId);
    StudentMetricsDto getMoreStudentData(Long studentId, List<ProgressResponseDto> studentEvents);
}
