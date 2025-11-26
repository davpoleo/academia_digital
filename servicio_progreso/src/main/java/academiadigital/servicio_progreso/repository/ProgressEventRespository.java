package academiadigital.servicio_progreso.repository;

import academiadigital.servicio_progreso.model.ProgressEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProgressEventRespository extends JpaRepository<ProgressEvent, Long>, JpaSpecificationExecutor<ProgressEvent> {
    List<ProgressEvent> findByCourseId(Long courseId);
}
