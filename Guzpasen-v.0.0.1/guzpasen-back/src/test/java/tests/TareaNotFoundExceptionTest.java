package tests;

import org.junit.jupiter.api.Test;
import guzpasen.exceptions.TareaNotFoundException;
import static org.junit.jupiter.api.Assertions.*;

class TareaNotFoundExceptionTest {

    @Test
    void testDefaultConstructor() {
        TareaNotFoundException ex = new TareaNotFoundException();
        assertNull(ex.getMessage()); // No se proporciona mensaje en el constructor vacío
    }

    @Test
    void testConstructorWithId() {
        Long idTarea = 42L;
        TareaNotFoundException ex = new TareaNotFoundException(idTarea);
        assertEquals("Tarea no encontrada: 42", ex.getMessage());
    }
}
