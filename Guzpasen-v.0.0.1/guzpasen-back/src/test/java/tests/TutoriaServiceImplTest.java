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

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guzpasen.exceptions.TutoriaNotFoundException;
import guzpasen.models.Alumno;
import guzpasen.models.Tutoria;
import guzpasen.models.Tutoria.Estado;
import guzpasen.repositories.AlumnoRepository;
import guzpasen.repositories.TutoriaRepository;
import guzpasen.services.TutoriaServiceImpl;
import jakarta.persistence.EntityNotFoundException;

class TutoriaServiceImplTest {

    @Mock
    private TutoriaRepository tutoriaRepository;

    @Mock
    private AlumnoRepository alumnoRepository;

    @InjectMocks
    private TutoriaServiceImpl tutoriaService;

    private Tutoria tutoria;
    private Alumno alumno;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        alumno = new Alumno();
        alumno.setDni("12345678A");
        alumno.setNombre("Juan");
        
        tutoria = new Tutoria();
        tutoria.setIdTutoria(1L);
        tutoria.setAlumno(alumno);
        tutoria.setAsignatura("Matemáticas");
        tutoria.setEstado(Estado.PENDIENTE);
        tutoria.setFecha(LocalDate.now());
        tutoria.setMotivo("Consulta dudas");
        tutoria.setUrgencia("Media");
    }

    @Test
    void testCreateTutoria_Success() {
        when(alumnoRepository.findById("12345678A")).thenReturn(Optional.of(alumno));
        when(tutoriaRepository.save(any(Tutoria.class))).thenAnswer(i -> i.getArgument(0));

        Tutoria creada = tutoriaService.createTutoria(tutoria);

        assertNotNull(creada);
        assertEquals("Matemáticas", creada.getAsignatura());
        assertEquals(alumno, creada.getAlumno());

        verify(alumnoRepository).findById("12345678A");
        verify(tutoriaRepository).save(any(Tutoria.class));
    }

    @Test
    void testCreateTutoria_AlumnoNotFound() {
        when(alumnoRepository.findById("12345678A")).thenReturn(Optional.empty());

        EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class, () -> {
            tutoriaService.createTutoria(tutoria);
        });

        assertTrue(thrown.getMessage().contains("Alumno no encontrado con DNI"));

        verify(alumnoRepository).findById("12345678A");
        verify(tutoriaRepository, never()).save(any());
    }

    @Test
    void testFindByIdTutoria() {
        when(tutoriaRepository.findByIdTutoria(1L)).thenReturn(Optional.of(tutoria));

        Optional<Tutoria> opt = tutoriaService.findByIdTutoria(1L);

        assertTrue(opt.isPresent());
        assertEquals("Matemáticas", opt.get().getAsignatura());
    }

    @Test
    void testFindByEstado() {
        when(tutoriaRepository.findByEstado(Estado.PENDIENTE)).thenReturn(List.of(tutoria));

        List<Tutoria> lista = tutoriaService.findByEstado(Estado.PENDIENTE);

        assertFalse(lista.isEmpty());
        assertEquals(1, lista.size());
    }

    @Test
    void testUpdateTutoria_Success() {
        Tutoria tutoriaUpdate = new Tutoria();
        tutoriaUpdate.setIdTutoria(1L);
        tutoriaUpdate.setAsignatura("Física");
        tutoriaUpdate.setMotivo("Nueva razón");
        tutoriaUpdate.setEstado(Estado.PENDIENTE);

        when(tutoriaRepository.findById(1L)).thenReturn(Optional.of(tutoria));
        when(tutoriaRepository.save(any(Tutoria.class))).thenAnswer(i -> i.getArgument(0));

        Tutoria result = tutoriaService.updateTutoria(tutoriaUpdate);

        assertEquals("Física", result.getAsignatura());
        assertEquals("Nueva razón", result.getMotivo());
        assertEquals(Estado.PENDIENTE, result.getEstado());

        verify(tutoriaRepository).findById(1L);
        verify(tutoriaRepository).save(any(Tutoria.class));
    }

    @Test
    void testUpdateTutoria_NotFound() {
        when(tutoriaRepository.findById(1L)).thenReturn(Optional.empty());

        Tutoria tutoriaUpdate = new Tutoria();
        tutoriaUpdate.setIdTutoria(1L);

        assertThrows(TutoriaNotFoundException.class, () -> {
            tutoriaService.updateTutoria(tutoriaUpdate);
        });

        verify(tutoriaRepository).findById(1L);
        verify(tutoriaRepository, never()).save(any());
    }

    @Test
    void testDeleteTutoria() {
        doNothing().when(tutoriaRepository).delete(tutoria);

        Tutoria borrada = tutoriaService.deleteTutoria(tutoria);

        assertEquals(tutoria, borrada);

        verify(tutoriaRepository).delete(tutoria);
    }
}
