package academiadigital.servicio_progreso.repository;

import academiadigital.servicio_progreso.model.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ProgressRepository extends JpaRepository<Progress, Long>, JpaSpecificationExecutor<Progress> {
    Optional<Progress> findByStudentIdAndCourseId(Long studentId, Long courseId);
}
