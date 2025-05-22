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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guzpasen.exceptions.ActaNotFoundException;
import guzpasen.models.Acta;
import guzpasen.repositories.ActaRespository;
import guzpasen.services.ActaServiceImpl;

class ActaServiceImplTest {

    @Mock
    private ActaRespository actaRespository;

    @InjectMocks
    private ActaServiceImpl actaService;

    private Acta acta1;
    private Acta acta2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        acta1 = new Acta(1L, "Puntos A", "Obs A", LocalDate.of(2025, 5, 21));
        acta2 = new Acta(2L, "Puntos B", "Obs B", LocalDate.of(2025, 5, 22));
    }

    @Test
    void testCreateActa() {
        when(actaRespository.save(acta1)).thenReturn(acta1);

        Acta created = actaService.createActa(acta1);

        assertNotNull(created);
        assertEquals("Puntos A", created.getPuntosTratados());
        verify(actaRespository).save(acta1);
    }

    @Test
    void testDeleteActa() {
        doNothing().when(actaRespository).delete(acta1);

        Acta deleted = actaService.deleteActa(acta1);

        assertEquals(acta1, deleted);
        verify(actaRespository).delete(acta1);
    }

    @Test
    void testFindAll() {
        when(actaRespository.findAll()).thenReturn(Arrays.asList(acta1, acta2));

        List<Acta> all = actaService.findAll();

        assertEquals(2, all.size());
        verify(actaRespository).findAll();
    }

    @Test
    void testFindByIdActaFound() {
        when(actaRespository.findByIdActa(1L)).thenReturn(Optional.of(acta1));

        Optional<Acta> found = actaService.findByIdActa(1L);

        assertTrue(found.isPresent());
        assertEquals("Puntos A", found.get().getPuntosTratados());
        verify(actaRespository).findByIdActa(1L);
    }

    @Test
    void testFindByIdActaNotFound() {
        when(actaRespository.findByIdActa(3L)).thenReturn(Optional.empty());

        Optional<Acta> found = actaService.findByIdActa(3L);

        assertFalse(found.isPresent());
        verify(actaRespository).findByIdActa(3L);
    }

    @Test
    void testFindByFecha() {
        LocalDate fecha = LocalDate.of(2025, 5, 21);
        when(actaRespository.findByFecha(fecha)).thenReturn(Arrays.asList(acta1));

        List<Acta> byFecha = actaService.findByFecha(fecha);

        assertEquals(1, byFecha.size());
        assertEquals(fecha, byFecha.get(0).getFecha());
        verify(actaRespository).findByFecha(fecha);
    }

    @Test
    void testUpdateActaSuccess() {
        Acta updateData = new Acta(1L, "Nuevos puntos", "Nuevas obs", LocalDate.of(2025, 6, 1));

        when(actaRespository.findById(1L)).thenReturn(Optional.of(acta1));
        when(actaRespository.save(any(Acta.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Acta updated = actaService.updateActa(updateData);

        assertEquals("Nuevos puntos", updated.getPuntosTratados());
        assertEquals("Nuevas obs", updated.getObservaciones());
        assertEquals(LocalDate.of(2025, 6, 1), updated.getFecha());

        verify(actaRespository).findById(1L);
        verify(actaRespository).save(any(Acta.class));
    }

    @Test
    void testUpdateActaNotFound() {
        Acta updateData = new Acta(3L, "Nuevos puntos", "Nuevas obs", LocalDate.of(2025, 6, 1));

        when(actaRespository.findById(3L)).thenReturn(Optional.empty());

        assertThrows(ActaNotFoundException.class, () -> actaService.updateActa(updateData));

        verify(actaRespository).findById(3L);
        verify(actaRespository, never()).save(any());
    }
}
