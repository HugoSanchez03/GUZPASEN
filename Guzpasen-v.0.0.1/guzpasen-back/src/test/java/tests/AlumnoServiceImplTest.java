package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guzpasen.exceptions.AlumnoNotFoundException;
import guzpasen.models.Alumno;
import guzpasen.repositories.AlumnoRepository;
import guzpasen.services.AlumnoServiceImpl;

class AlumnoServiceImplTest {

    @Mock
    private AlumnoRepository alumnoRepository;

    @InjectMocks
    private AlumnoServiceImpl alumnoService;

    private Alumno alumno1;
    private Alumno alumno2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        alumno1 = new Alumno("12345678A", "Juan", "Pérez", "Tutor Juan", "Apellido Tutor", "tutor1@email.com");
        alumno2 = new Alumno("87654321B", "Ana", "García", "Tutor Ana", "Apellido Tutor 2", "tutor2@email.com");
    }

    @Test
    void testCreateAlumno() {
        when(alumnoRepository.save(alumno1)).thenReturn(alumno1);

        Alumno created = alumnoService.createAlumno(alumno1);

        assertNotNull(created);
        assertEquals("Juan", created.getNombre());
        verify(alumnoRepository).save(alumno1);
    }

    @Test
    void testDeleteAlumno() {
        doNothing().when(alumnoRepository).delete(alumno1);

        Alumno deleted = alumnoService.deleteAlumno(alumno1);

        assertEquals(alumno1, deleted);
        verify(alumnoRepository).delete(alumno1);
    }

    @Test
    void testFindAll() {
        when(alumnoRepository.findAll()).thenReturn(Arrays.asList(alumno1, alumno2));

        List<Alumno> all = alumnoService.findAll();

        assertEquals(2, all.size());
        verify(alumnoRepository).findAll();
    }

    @Test
    void testFindByDniFound() {
        when(alumnoRepository.findByDni("12345678A")).thenReturn(Optional.of(alumno1));

        Optional<Alumno> found = alumnoService.findByDni("12345678A");

        assertTrue(found.isPresent());
        assertEquals("Juan", found.get().getNombre());
        verify(alumnoRepository).findByDni("12345678A");
    }

    @Test
    void testFindByDniNotFound() {
        when(alumnoRepository.findByDni("00000000Z")).thenReturn(Optional.empty());

        Optional<Alumno> found = alumnoService.findByDni("00000000Z");

        assertFalse(found.isPresent());
        verify(alumnoRepository).findByDni("00000000Z");
    }

    @Test
    void testUpdateAlumnoSuccess() {
        Alumno updateData = new Alumno("12345678A", "Juan Actualizado", "Pérez", "Tutor Nuevo", "Apellido Nuevo", "nuevo@email.com");

        when(alumnoRepository.findById("12345678A")).thenReturn(Optional.of(alumno1));
        when(alumnoRepository.save(any(Alumno.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Alumno updated = alumnoService.updateAlumno(updateData);

        assertEquals("Juan Actualizado", updated.getNombre());
        assertEquals("Tutor Nuevo", updated.getNombreTutorLegal());
        assertEquals("nuevo@email.com", updated.getEmailTutorLegal());

        verify(alumnoRepository).findById("12345678A");
        verify(alumnoRepository).save(any(Alumno.class));
    }

    @Test
    void testUpdateAlumnoNotFound() {
        Alumno updateData = new Alumno("00000000Z", "No Existe", "Apellido", "Tutor", "Tutor Apellido", "email@no.com");

        when(alumnoRepository.findById("00000000Z")).thenReturn(Optional.empty());

        assertThrows(AlumnoNotFoundException.class, () -> alumnoService.updateAlumno(updateData));

        verify(alumnoRepository).findById("00000000Z");
        verify(alumnoRepository, never()).save(any());
    }
}
