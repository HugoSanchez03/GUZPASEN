package tests;

import org.junit.jupiter.api.Test;

import guzpasen.models.Acta;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ActaTest {

    @Test
    void testConstructorAndGetters() {
        LocalDate fecha = LocalDate.of(2025, 5, 21);
        Acta acta = new Acta(1L, "Punto A, Punto B", "Sin observaciones", fecha);

        assertEquals(1L, acta.getIdActa());
        assertEquals("Punto A, Punto B", acta.getPuntosTratados());
        assertEquals("Sin observaciones", acta.getObservaciones());
        assertEquals(fecha, acta.getFecha());
    }

    @Test
    void testSetters() {
        Acta acta = new Acta();
        LocalDate fecha = LocalDate.of(2024, 10, 10);

        acta.setIdActa(2L);
        acta.setPuntosTratados("Punto C");
        acta.setObservaciones("Observación adicional");
        acta.setFecha(fecha);

        assertEquals(2L, acta.getIdActa());
        assertEquals("Punto C", acta.getPuntosTratados());
        assertEquals("Observación adicional", acta.getObservaciones());
        assertEquals(fecha, acta.getFecha());
    }

    @Test
    void testEqualsAndHashCode() {
        LocalDate fecha = LocalDate.of(2025, 1, 1);
        Acta acta1 = new Acta(1L, "A", "B", fecha);
        Acta acta2 = new Acta(1L, "A", "B", fecha);

        assertEquals(acta1, acta2);
        assertEquals(acta1.hashCode(), acta2.hashCode());
    }

    @Test
    void testToString() {
        LocalDate fecha = LocalDate.of(2025, 1, 1);
        Acta acta = new Acta(1L, "Tratados", "Observaciones", fecha);

        String expected = "Acta(idActa=1, puntosTratados=Tratados, observaciones=Observaciones, fecha=2025-01-01)";
        assertEquals(expected, acta.toString());
    }
}
