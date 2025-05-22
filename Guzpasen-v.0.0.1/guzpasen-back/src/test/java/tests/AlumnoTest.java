package tests;

import org.junit.jupiter.api.Test;

import guzpasen.models.Alumno;

import static org.junit.jupiter.api.Assertions.*;

class AlumnoTest {

    @Test
    void testConstructorAndGetters() {
        Alumno alumno = new Alumno(
                "12345678A",
                "Ana",
                "García López",
                "Luis",
                "García Fernández",
                "luis.tutor@email.com"
        );

        assertEquals("12345678A", alumno.getDni());
        assertEquals("Ana", alumno.getNombre());
        assertEquals("García López", alumno.getApellidos());
        assertEquals("Luis", alumno.getNombreTutorLegal());
        assertEquals("García Fernández", alumno.getApellidosTutorLegal());
        assertEquals("luis.tutor@email.com", alumno.getEmailTutorLegal());
    }

    @Test
    void testSetters() {
        Alumno alumno = new Alumno();
        alumno.setDni("87654321B");
        alumno.setNombre("Carlos");
        alumno.setApellidos("Martínez Ruiz");
        alumno.setNombreTutorLegal("Lucía");
        alumno.setApellidosTutorLegal("Ruiz Morales");
        alumno.setEmailTutorLegal("lucia.tutor@email.com");

        assertEquals("87654321B", alumno.getDni());
        assertEquals("Carlos", alumno.getNombre());
        assertEquals("Martínez Ruiz", alumno.getApellidos());
        assertEquals("Lucía", alumno.getNombreTutorLegal());
        assertEquals("Ruiz Morales", alumno.getApellidosTutorLegal());
        assertEquals("lucia.tutor@email.com", alumno.getEmailTutorLegal());
    }

    @Test
    void testEqualsAndHashCode() {
        Alumno alumno1 = new Alumno("12345678A", "Ana", "García", "Luis", "García", "correo@ejemplo.com");
        Alumno alumno2 = new Alumno("12345678A", "Ana", "García", "Luis", "García", "correo@ejemplo.com");

        assertEquals(alumno1, alumno2);
        assertEquals(alumno1.hashCode(), alumno2.hashCode());
    }

    @Test
    void testToString() {
        Alumno alumno = new Alumno("12345678A", "Ana", "García", "Luis", "García", "correo@ejemplo.com");

        String expected = "Alumno(dni=12345678A, nombre=Ana, apellidos=García, nombreTutorLegal=Luis, apellidosTutorLegal=García, emailTutorLegal=correo@ejemplo.com)";
        assertEquals(expected, alumno.toString());
    }
}
