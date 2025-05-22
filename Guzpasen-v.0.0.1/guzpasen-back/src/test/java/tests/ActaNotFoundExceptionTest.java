package tests;

import org.junit.jupiter.api.Test;
import guzpasen.exceptions.ActaNotFoundException;
import static org.junit.jupiter.api.Assertions.*;

class ActaNotFoundExceptionTest {

    @Test
    void testDefaultConstructor() {
        ActaNotFoundException ex = new ActaNotFoundException();
        assertNull(ex.getMessage()); // El constructor vacío no tiene mensaje
    }

    @Test
    void testConstructorWithId() {
        Long id = 42L;
        ActaNotFoundException ex = new ActaNotFoundException(id);
        assertEquals("Acta no encontrada: 42", ex.getMessage());
    }
}
