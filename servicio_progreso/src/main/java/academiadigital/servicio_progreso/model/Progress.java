package academiadigital.servicio_progreso.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "progress")
@Data
public class Progress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "El Id del estudiante no puede ser null")
    @Positive(message = "El ID del estudiante debe ser un número positivo")
    private Long studentId;
    @NotNull(message = "El Id del estudiante no puede ser null")
    @Positive(message = "El ID del curso debe ser un número positivo")
    private Long CourseId;
    @NotNull(message = "El Id del estudiante no puede ser null")
    @Min(value = 0, message = "El porcentaje no puede ser menor a 0")
    @Max(value = 100, message = "El porcentaje no puede ser mayor a 100")
    private Integer progressPercentage;
    @NotNull(message = "La fecha de actualización es obligatoria")
    private LocalDateTime updateDate;

}
