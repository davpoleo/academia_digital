package academiadigital.servicio_progreso.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "progress_events")
@Data
public class ProgressEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "El Id del estudiante no puede ser null")
    @Positive(message = "El ID del curso debe ser un número positivo")
    private Long courseId;
    @NotNull(message = "El Id del estudiante no puede ser null")
    @Positive(message = "El ID del estudiante debe ser un número positivo")
    private Long studentId;
    @NotNull(message = "El campo no puede estar vacio.")
    private String eventType;
    @NotNull(message = "El campo no puede estar vacio.")
    private String value;
    @NotNull(message = "La fecha del registro es obligatoria")
    private LocalDateTime timestamp;
}