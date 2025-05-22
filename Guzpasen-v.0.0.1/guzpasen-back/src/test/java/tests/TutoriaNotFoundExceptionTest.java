package tests;

import org.junit.jupiter.api.Test;
import guzpasen.exceptions.TutoriaNotFoundException;
import static org.junit.jupiter.api.Assertions.*;

class TutoriaNotFoundExceptionTest {

    @Test
    void testDefaultConstructor() {
        TutoriaNotFoundException ex = new TutoriaNotFoundException();
        assertNull(ex.getMessage()); // Constructor sin mensaje devuelve null
    }

    @Test
    void testConstructorWithId() {
        Long idTutoria = 100L;
        TutoriaNotFoundException ex = new TutoriaNotFoundException(idTutoria);
        assertEquals("Tutoria no encontrada: 100", ex.getMessage());
    }
}
