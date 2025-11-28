package academiadigital.servicio_reporte.service.impl;

import academiadigital.servicio_reporte.client.CourseFeingClient;
import academiadigital.servicio_reporte.client.ProgressFeingClient;
import academiadigital.servicio_reporte.client.StudentFeingClient;
import academiadigital.servicio_reporte.dto.*;
import academiadigital.servicio_reporte.exception.ResourceNotFoundException;
import academiadigital.servicio_reporte.service.ReportService;
import academiadigital.servicio_reporte.util.ApiConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
//@Slf4j
@Service
public class ReportServiceImpl implements ReportService {

    private final StudentFeingClient studentFeingClient;
    private final CourseFeingClient courseFeingClient;
    private final ProgressFeingClient progressFeingClient;


    @Override
    public AnalyticalReportDto generateAnalyticalReport(Long courseId) {
        //log.info("Iniciando generación de reporte analítico para el curso ID: {}", courseId);

        //FeingClients de Course y ProgressEvent
        CourseResponseDto courseInformation = courseFeingClient.getCourseInformationById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException(ApiConstants.EXCEPTION_COURSE_ID_NO_EXIST));
        List<ProgressResponseDto> rawEvents = Collections.singletonList(progressFeingClient.getRawEventsByCourse(courseId)
                .orElseThrow(() -> new ResourceNotFoundException(ApiConstants.EXCEPTION_COURSE_ID_NO_EXIST)));

        //Agrupacion de datos crudos por estudiante
        Map<Long, List<ProgressResponseDto>> eventsByStudent = rawEvents.stream()
                .collect(Collectors.groupingBy(ProgressResponseDto::studentId));

        List<StudentMetricsDto> metrics = eventsByStudent.entrySet().parallelStream()
                .map(entry -> getMoreStudentData(entry.getKey(), entry.getValue()))
                .toList();

        AnalyticalReportDto reportDto = new AnalyticalReportDto(courseId, courseInformation.title(), metrics);

        //log.info("Reporte analítico generado exitosamente para {} estudiantes.", metrics.size());
        return reportDto;
    }

    @Override
    public StudentMetricsDto getMoreStudentData(Long studentId, List<ProgressResponseDto> studentEvents) {
        return null;
    }
}
