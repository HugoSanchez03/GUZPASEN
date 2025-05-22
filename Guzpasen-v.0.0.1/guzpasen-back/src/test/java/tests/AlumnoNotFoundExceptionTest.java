package tests;

import org.junit.jupiter.api.Test;
import guzpasen.exceptions.AlumnoNotFoundException;
import static org.junit.jupiter.api.Assertions.*;

class AlumnoNotFoundExceptionTest {

    @Test
    void testDefaultConstructor() {
        AlumnoNotFoundException ex = new AlumnoNotFoundException();
        assertNull(ex.getMessage()); // El constructor vacío no genera mensaje
    }

    @Test
    void testConstructorWithDni() {
        String dni = "12345678A";
        AlumnoNotFoundException ex = new AlumnoNotFoundException(dni);
        assertEquals("Alumno no encontrado: 12345678A", ex.getMessage());
    }
}
