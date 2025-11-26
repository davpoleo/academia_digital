package academiadigital.servicio_progreso.service.impl;

import academiadigital.servicio_progreso.client.CourseFeingClient;
import academiadigital.servicio_progreso.client.StudentFeingClient;
import academiadigital.servicio_progreso.dto.CourseResponseDto;
import academiadigital.servicio_progreso.dto.ProgressRequestDto;
import academiadigital.servicio_progreso.dto.ProgressResponseDto;
import academiadigital.servicio_progreso.dto.StudentResponseDto;
import academiadigital.servicio_progreso.exception.ResourceNotFoundException;
import academiadigital.servicio_progreso.mapper.ProgressMapper;
import academiadigital.servicio_progreso.model.Progress;
import academiadigital.servicio_progreso.repository.ProgressRepository;
import academiadigital.servicio_progreso.service.ProgressService;
import academiadigital.servicio_progreso.util.ApiConstants;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class ProgressServiceImpl implements ProgressService {

    private final ProgressRepository progressRepository;
    private final CourseFeingClient courseFeingClient;
    private final StudentFeingClient studentFeingClient;
    private final ProgressMapper progressMapper;

    @Override
    @Transactional(readOnly = true)
    public ProgressResponseDto getProgressByStudentId(Long studentId) {
        log.info("Realizando busqueda de progreso por Id de estudiante: {}", studentId);
        Progress searchProgressByStudentId = progressRepository.findByStudentId(studentId)
                .orElseThrow(()-> new ResourceNotFoundException(ApiConstants.BUSINESS_ERR_ID_NOT_FOUND));
        log.info("Progreso de estudiante  {} encontrado", studentId);
        return progressMapper.mapToProgressDto(searchProgressByStudentId);
    }

    @Override
    public List<ProgressResponseDto> getAllProgress() {
        log.info("Realizando busqueda de todos el progreso academico registrado");
        return progressRepository.findAll().stream()
                .map(progressMapper::mapToProgressDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProgressResponseDto CreateOrUpdateProgress(ProgressRequestDto requestDto) {
        try {
            log.info("Consultando existencia de curso con ID: {}", requestDto.courseId());
            CourseResponseDto courseResponseDto = courseFeingClient.getCourseById(requestDto.courseId());
            log.info("Curso con ID: {} encontrado", requestDto.courseId());
        } catch (FeignException.NotFound error) {
            log.info("Curso con ID: {} no existe", requestDto.courseId());
            throw new ResourceNotFoundException(ApiConstants.BUSINESS_ERR_ID_NOT_FOUND);
        }

        try {
            log.info("Consultando existencia de estudiante con ID: {}", requestDto.studentId());
            StudentResponseDto studentResponseDto = studentFeingClient.getStudenById(requestDto.studentId());
            log.info("Estudiante con ID: {} encontrado", requestDto.studentId());
        } catch (Exception e) {
            log.info("Estudiante con ID: {} no existe", requestDto.studentId());
            throw new ResourceNotFoundException(ApiConstants.BUSINESS_ERR_ID_NOT_FOUND);
        }

        Optional<Progress> progressExist = progressRepository.findByStudentIdAndCourseId(
                requestDto.studentId(),
                requestDto.courseId()
        );

        Progress progress = new Progress();
        if (progressExist.isPresent()){
            //Si existe el id lo uso para el update
            progress = progressExist.get();
        }else{
            //Si no existe creo uno nuevo con los IDs
            progress.setCourseId(requestDto.courseId());
            progress.setStudentId(requestDto.studentId());
            progress.setProgressPercentage(requestDto.progressPercentage());
            progress.setUpdateDate(LocalDateTime.now());
            Progress newProgress = progressRepository.save(progress);
            return progressMapper.mapToProgressDto(newProgress);
        }

        progress.setProgressPercentage(requestDto.progressPercentage());
        progress.setUpdateDate(LocalDateTime.now());
        Progress newProgress = progressRepository.save(progress);
        return progressMapper.mapToProgressDto(newProgress);
    }

    @Override
    public void deleteProgress(Long courseId, Long studentId) {
        log.info("Intentando eliminar progreso de Estudiante ID: {} del Curso ID: {}", studentId, courseId);
        Optional<Progress> progressCheck = progressRepository.findByStudentIdAndCourseId(studentId, courseId);
        if (progressCheck.isEmpty()){
            log.warn("Inscripción no encontrada para Estudiante ID: {} y Curso ID: {}", studentId, courseId);
            throw new ResourceNotFoundException(ApiConstants.BUSINESS_ERR_ID_NOT_FOUND);
        }
        log.info("Inscripción eliminada exitosamente.");
        progressRepository.delete(progressCheck.get());
    }

    // OLD_CODE
    //public List<ProgresoDto> obtenerProgresoPorEstudiante(Long estudianteId){
    //    List<Progreso> progresos = progresoRepository.findByEstudianteId(estudianteId);
    //    return progresos.stream()
    //            .map(this::mapearProgresoADto)
    //            .toList();
    //}


}
