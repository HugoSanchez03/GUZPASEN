package guzpasen.models;

import jakarta.persistence.*;
import lombok.*;

/**
 * Representa a un alumno dentro del sistema Guzpasen.
 * Contiene la información personal del alumno y de su tutor legal.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity(name = "alumno")
public class Alumno {

    /**
     * DNI del alumno. Se utiliza como identificador único.
     */
    @Id
    @Column(name = "dni")
    private String dni;

    /**
     * Nombre del alumno.
     */
    @Column(name = "nombre")
    private String nombre;

    /**
     * Apellidos del alumno.
     */
    @Column(name = "apellidos")
    private String apellidos;

    /**
     * Nombre del tutor legal del alumno.
     */
    @Column(name = "nombre_tutor_legal")
    private String nombreTutorLegal;

    /**
     * Apellidos del tutor legal del alumno.
     */
    @Column(name = "apellidos_tutor_legal")
    private String apellidosTutorLegal;

    /**
     * Correo electrónico del tutor legal del alumno.
     */
    @Column(name = "email_tutor_legal")
    private String emailTutorLegal;
}
