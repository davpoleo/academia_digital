package academiadigital.servicio_curso.repository;

import academiadigital.servicio_curso.model.Course;
import academiadigital.servicio_curso.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long>, JpaSpecificationExecutor<Enrollment> {
    boolean existsByCourseId(Long id);
    Optional<Enrollment> findByStudentIdAndCourseId(Long studentId, Long courseId);
}
