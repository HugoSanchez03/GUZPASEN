package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import guzpasen.models.Acta;
import guzpasen.models.Alumno;
import guzpasen.models.Tutoria;
import guzpasen.models.Usuario;

class TutoriaTest {

    @Test
    void testConstructorAndGetters() {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1L);

        Alumno alumno = new Alumno();
        alumno.setDni("12345678A");

        Acta acta = new Acta();
        acta.setIdActa(10L);

        Tutoria tutoria = new Tutoria(
                100L,
                "Seguimiento académico",
                "Alta",
                "Matemáticas",
                LocalDate.of(2025, 5, 22),
                Tutoria.Estado.PENDIENTE,
                "Se comentó el bajo rendimiento",
                usuario,
                alumno,
                acta
        );

        assertEquals(100L, tutoria.getIdTutoria());
        assertEquals("Seguimiento académico", tutoria.getMotivo());
        assertEquals("Alta", tutoria.getUrgencia());
        assertEquals("Matemáticas", tutoria.getAsignatura());
        assertEquals(LocalDate.of(2025, 5, 22), tutoria.getFecha());
        assertEquals(Tutoria.Estado.PENDIENTE, tutoria.getEstado());
        assertEquals("Se comentó el bajo rendimiento", tutoria.getObservaciones());
        assertEquals(usuario, tutoria.getUsuario());
        assertEquals(alumno, tutoria.getAlumno());
        assertEquals(acta, tutoria.getActa());
    }

    @Test
    void testSetters() {
        Tutoria tutoria = new Tutoria();
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(2L);

        Alumno alumno = new Alumno();
        alumno.setDni("98765432B");

        Acta acta = new Acta();
        acta.setIdActa(20L);

        tutoria.setIdTutoria(200L);
        tutoria.setMotivo("Revisión de conducta");
        tutoria.setUrgencia("Media");
        tutoria.setAsignatura("Lengua");
        tutoria.setFecha(LocalDate.of(2025, 6, 15));
        tutoria.setEstado(Tutoria.Estado.REALIZADA);
        tutoria.setObservaciones("Buena actitud");
        tutoria.setUsuario(usuario);
        tutoria.setAlumno(alumno);
        tutoria.setActa(acta);

        assertEquals(200L, tutoria.getIdTutoria());
        assertEquals("Revisión de conducta", tutoria.getMotivo());
        assertEquals("Media", tutoria.getUrgencia());
        assertEquals("Lengua", tutoria.getAsignatura());
        assertEquals(LocalDate.of(2025, 6, 15), tutoria.getFecha());
        assertEquals(Tutoria.Estado.REALIZADA, tutoria.getEstado());
        assertEquals("Buena actitud", tutoria.getObservaciones());
        assertEquals(usuario, tutoria.getUsuario());
        assertEquals(alumno, tutoria.getAlumno());
        assertEquals(acta, tutoria.getActa());
    }

    @Test
    void testEqualsAndHashCode() {
        Tutoria tutoria1 = new Tutoria();
        tutoria1.setIdTutoria(1L);

        Tutoria tutoria2 = new Tutoria();
        tutoria2.setIdTutoria(1L);

        assertEquals(tutoria1, tutoria2);
        assertEquals(tutoria1.hashCode(), tutoria2.hashCode());
    }

    @Test
    void testToStringContainsKeyFields() {
        Tutoria tutoria = new Tutoria();
        tutoria.setMotivo("Cambio de grupo");
        tutoria.setUrgencia("Baja");
        tutoria.setAsignatura("Física");
        tutoria.setEstado(Tutoria.Estado.PENDIENTE);

        String result = tutoria.toString();

        assertTrue(result.contains("Cambio de grupo"));
        assertTrue(result.contains("Física"));
        assertTrue(result.contains("PENDIENTE"));
    }

    @Test
    void testEnumEstado() {
        assertEquals("PENDIENTE", Tutoria.Estado.PENDIENTE.name());
        assertEquals("REALIZADA", Tutoria.Estado.REALIZADA.name());
    }
}
