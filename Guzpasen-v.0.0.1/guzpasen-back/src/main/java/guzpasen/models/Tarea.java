package guzpasen.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

/**
 * Representa una tarea asociada a una tutoría dentro del sistema Guzpasen.
 * Cada tarea incluye información sobre la asignatura, su descripción, estado y fecha.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity(name = "tarea")
public class Tarea {

    /**
     * Identificador único de la tarea. Se genera automáticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tarea")
    private Long idTarea;

    /**
     * Asignatura relacionada con la tarea.
     */
    @Column(name = "asignatura")
    private String asignatura;

    /**
     * Descripción de la tarea asignada.
     */
    @Column(name = "descripcion")
    private String descripcion;

    /**
     * Estado actual de la tarea: puede estar completada o pendiente.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private Estado estado;

    /**
     * Fecha en la que se debe realizar o se registró la tarea.
     */
    @Column(name = "fecha_tarea")
    private LocalDate fechaTarea;

    /**
     * Tutoría a la que pertenece esta tarea.
     */
    @ManyToOne
    @JoinColumn(name = "id_tutoria")
    private Tutoria tutoria;

    /**
     * Enum que representa los posibles estados de una tarea.
     */
    public enum Estado {
        /** La tarea ha sido completada. */
        COMPLETADA,

        /** La tarea está pendiente de realización. */
        PENDIENTE
    }
}
