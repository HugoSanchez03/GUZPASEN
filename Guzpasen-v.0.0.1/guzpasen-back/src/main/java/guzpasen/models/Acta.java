package guzpasen.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

/**
 * Representa un acta dentro del sistema Guzpasen.
 * Un acta recoge los puntos tratados y observaciones de una tutoría en una fecha determinada.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity(name = "acta")
public class Acta {

    /**
     * Identificador único del acta. Se genera automáticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_acta")
    private Long idActa;

    /**
     * Descripción de los puntos tratados durante la tutoría.
     */
    @Column(name = "puntos_tratados")
    private String puntosTratados;

    /**
     * Observaciones adicionales relacionadas con la tutoría.
     */
    @Column(name = "observaciones")
    private String observaciones;

    /**
     * Fecha en la que se registró el acta.
     */
    @Column(name = "fecha")
    private LocalDate fecha;
}
