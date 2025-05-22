package guzpasen.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

/**
 * Representa una tutoría en el sistema Guzpasen.
 * Una tutoría incluye información sobre el motivo, urgencia, asignatura, estado, observaciones,
 * y está relacionada con un usuario, un alumno y un acta.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity(name = "tutoria")
public class Tutoria {

    /**
     * Identificador único de la tutoría. Se genera automáticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tutoria")
    private Long idTutoria;

    /**
     * Motivo principal por el que se solicita la tutoría.
     */
    @Column(name = "motivo")
    private String motivo;

    /**
     * Nivel de urgencia de la tutoría.
     */
    @Column(name = "urgencia")
    private String urgencia;

    /**
     * Asignatura relacionada con la tutoría.
     */
    @Column(name = "asignatura")
    private String asignatura;

    /**
     * Fecha programada para la tutoría.
     */
    @Column(name = "fecha")
    private LocalDate fecha;

    /**
     * Estado actual de la tutoría: puede estar pendiente o ya realizada.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private Estado estado;

    /**
     * Observaciones adicionales anotadas durante o después de la tutoría.
     */
    @Column(name = "observaciones")
    private String observaciones;

    /**
     * Usuario (profesor o tutor) que atiende la tutoría.
     */
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    /**
     * Alumno que solicita o participa en la tutoría.
     */
    @ManyToOne
    @JoinColumn(name = "dni_alumno")
    private Alumno alumno;

    /**
     * Acta asociada a la tutoría, si existe.
     */
    @ManyToOne
    @JoinColumn(name = "id_acta")
    private Acta acta;

    /**
     * Enum que representa los posibles estados de una tutoría.
     */
    public enum Estado {
        /** La tutoría está pendiente de realizarse. */
        PENDIENTE,

        /** La tutoría ya ha sido realizada. */
        REALIZADA
    }
}
