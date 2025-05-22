package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import guzpasen.models.Tarea;
import guzpasen.models.Tutoria;

class TareaTest {

    @Test
    void testConstructorAndGetters() {
        Tutoria tutoria = new Tutoria();
        tutoria.setIdTutoria(1L);

        Tarea tarea = new Tarea(
                100L,
                "Matemáticas",
                "Resolver ecuaciones de segundo grado",
                Tarea.Estado.PENDIENTE,
                LocalDate.of(2025, 5, 21),
                tutoria
        );

        assertEquals(100L, tarea.getIdTarea());
        assertEquals("Matemáticas", tarea.getAsignatura());
        assertEquals("Resolver ecuaciones de segundo grado", tarea.getDescripcion());
        assertEquals(Tarea.Estado.PENDIENTE, tarea.getEstado());
        assertEquals(LocalDate.of(2025, 5, 21), tarea.getFechaTarea());
        assertEquals(tutoria, tarea.getTutoria());
    }

    @Test
    void testSetters() {
        Tarea tarea = new Tarea();
        Tutoria tutoria = new Tutoria();
        tutoria.setIdTutoria(2L);

        tarea.setIdTarea(101L);
        tarea.setAsignatura("Lengua");
        tarea.setDescripcion("Redacción sobre un libro");
        tarea.setEstado(Tarea.Estado.COMPLETADA);
        tarea.setFechaTarea(LocalDate.of(2025, 6, 1));
        tarea.setTutoria(tutoria);

        assertEquals(101L, tarea.getIdTarea());
        assertEquals("Lengua", tarea.getAsignatura());
        assertEquals("Redacción sobre un libro", tarea.getDescripcion());
        assertEquals(Tarea.Estado.COMPLETADA, tarea.getEstado());
        assertEquals(LocalDate.of(2025, 6, 1), tarea.getFechaTarea());
        assertEquals(tutoria, tarea.getTutoria());
    }

    @Test
    void testEqualsAndHashCode() {
        Tutoria tutoria = new Tutoria();
        tutoria.setIdTutoria(1L);

        Tarea tarea1 = new Tarea(1L, "Música", "Aprender acordes", Tarea.Estado.PENDIENTE, LocalDate.now(), tutoria);
        Tarea tarea2 = new Tarea(1L, "Música", "Aprender acordes", Tarea.Estado.PENDIENTE, LocalDate.now(), tutoria);

        assertEquals(tarea1, tarea2);
        assertEquals(tarea1.hashCode(), tarea2.hashCode());
    }

    @Test
    void testToString() {
        Tutoria tutoria = new Tutoria();
        tutoria.setIdTutoria(1L);

        Tarea tarea = new Tarea(1L, "Física", "Estudiar cinemática", Tarea.Estado.COMPLETADA, LocalDate.of(2025, 5, 21), tutoria);

        String result = tarea.toString();

        assertTrue(result.contains("Física"));
        assertTrue(result.contains("Estudiar cinemática"));
        assertTrue(result.contains("COMPLETADA"));
        assertTrue(result.contains("2025-05-21"));
    }

    @Test
    void testEnumEstado() {
        assertEquals("COMPLETADA", Tarea.Estado.COMPLETADA.name());
        assertEquals("PENDIENTE", Tarea.Estado.PENDIENTE.name());
    }
}
