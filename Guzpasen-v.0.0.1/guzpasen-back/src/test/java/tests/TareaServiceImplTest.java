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

import guzpasen.exceptions.TareaNotFoundException;
import guzpasen.models.Tarea;
import guzpasen.models.Tarea.Estado;
import guzpasen.repositories.TareaRepository;
import guzpasen.services.TareaServiceImpl;

class TareaServiceImplTest {

    @Mock
    private TareaRepository tareaRepository;

    @InjectMocks
    private TareaServiceImpl tareaService;

    private Tarea tarea;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        tarea = new Tarea();
        tarea.setIdTarea(1L);
        tarea.setAsignatura("Matemáticas");
        tarea.setDescripcion("Resolver ejercicios");
        tarea.setEstado(Estado.PENDIENTE);
        tarea.setFechaTarea(LocalDate.now());
    }

    @Test
    void testCreateTarea() {
        when(tareaRepository.save(tarea)).thenReturn(tarea);

        Tarea created = tareaService.createTarea(tarea);
        assertNotNull(created);
        assertEquals(tarea.getAsignatura(), created.getAsignatura());

        verify(tareaRepository).save(tarea);
    }

    @Test
    void testFindByIdTarea() {
        when(tareaRepository.findByIdTarea(1L)).thenReturn(Optional.of(tarea));

        Optional<Tarea> found = tareaService.findByIdTarea(1L);
        assertTrue(found.isPresent());
        assertEquals("Matemáticas", found.get().getAsignatura());
    }

    @Test
    void testFindByEstado() {
        when(tareaRepository.findByEstado(Estado.PENDIENTE)).thenReturn(List.of(tarea));

        List<Tarea> tareas = tareaService.findByEstado(Estado.PENDIENTE);
        assertFalse(tareas.isEmpty());
        assertEquals(1, tareas.size());
        assertEquals("Matemáticas", tareas.get(0).getAsignatura());
    }

    @Test
    void testUpdateTareaSuccess() {
        Tarea updatedTarea = new Tarea();
        updatedTarea.setIdTarea(1L);
        updatedTarea.setAsignatura("Física");
        updatedTarea.setDescripcion("Leer capítulo 3");
        updatedTarea.setEstado(Estado.COMPLETADA);
        updatedTarea.setFechaTarea(LocalDate.now().plusDays(1));

        when(tareaRepository.findById(1L)).thenReturn(Optional.of(tarea));
        when(tareaRepository.save(any(Tarea.class))).thenAnswer(i -> i.getArgument(0));

        Tarea result = tareaService.updateTarea(updatedTarea);
        assertEquals("Física", result.getAsignatura());
        assertEquals(Estado.COMPLETADA, result.getEstado());
        assertEquals("Leer capítulo 3", result.getDescripcion());

        verify(tareaRepository).findById(1L);
        verify(tareaRepository).save(any(Tarea.class));
    }

    @Test
    void testUpdateTareaNotFound() {
        when(tareaRepository.findById(1L)).thenReturn(Optional.empty());

        Tarea tareaInexistente = new Tarea();
        tareaInexistente.setIdTarea(1L);

        assertThrows(TareaNotFoundException.class, () -> {
            tareaService.updateTarea(tareaInexistente);
        });

        verify(tareaRepository).findById(1L);
        verify(tareaRepository, never()).save(any());
    }

    @Test
    void testDeleteTarea() {
        doNothing().when(tareaRepository).delete(tarea);

        Tarea deleted = tareaService.deleteTarea(tarea);
        assertEquals(tarea, deleted);

        verify(tareaRepository).delete(tarea);
    }

}
