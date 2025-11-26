package academiadigital.servicio_progreso.service;

import academiadigital.servicio_progreso.dto.ProgressRequestDto;
import academiadigital.servicio_progreso.dto.ProgressResponseDto;

import java.util.List;

public interface ProgressService {
    ProgressResponseDto getProgressByStudentId(Long studentId);
    List<ProgressResponseDto> getAllProgress();
    ProgressResponseDto CreateOrUpdateProgress(ProgressRequestDto requestDto);
    void deleteProgress(Long courseId, Long studentId);
}
