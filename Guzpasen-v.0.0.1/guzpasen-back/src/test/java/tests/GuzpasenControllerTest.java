package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import guzpasen.controllers.GuzpasenController;
import guzpasen.exceptions.ActaNotFoundException;
import guzpasen.exceptions.AlumnoNotFoundException;
import guzpasen.exceptions.TareaNotFoundException;
import guzpasen.exceptions.TutoriaNotFoundException;
import guzpasen.models.Acta;
import guzpasen.models.Alumno;
import guzpasen.models.Tarea;
import guzpasen.models.Tutoria;
import guzpasen.models.Usuario;
import guzpasen.repositories.UsuarioRepository;
import guzpasen.services.ActaService;
import guzpasen.services.AlumnoService;
import guzpasen.services.JwtService;
import guzpasen.services.TareaService;
import guzpasen.services.TutoriaService;
import guzpasen.services.UsuarioService;

class GuzpasenControllerTest {

    @InjectMocks
    private GuzpasenController controller;

    @Mock
    private ActaService actaService;

    @Mock
    private AlumnoService alumnoService;

    @Mock
    private TareaService tareaService;

    @Mock
    private TutoriaService tutoriaService;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepo;

    @Mock
    private JwtService jwtService;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // === Tests para métodos POST (crear) ===

    @Test
    void testAddActa_SetsFechaIfNull() {
        Acta input = new Acta();
        input.setFecha(null);

        Acta saved = new Acta();
        saved.setFecha(LocalDate.now());

        when(actaService.createActa(any())).thenReturn(saved);

        ResponseEntity<Acta> response = controller.addActa(input);

        assertEquals(CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getFecha());
        verify(actaService).createActa(any(Acta.class));
    }

    @Test
    void testAddAlumno() {
        Alumno alumno = new Alumno();
        when(alumnoService.createAlumno(alumno)).thenReturn(alumno);

        ResponseEntity<Alumno> response = controller.addAlumno(alumno);

        assertEquals(CREATED, response.getStatusCode());
        assertEquals(alumno, response.getBody());
    }

    @Test
    void testAddTutoria() {
        Tutoria tutoria = new Tutoria();
        when(tutoriaService.createTutoria(tutoria)).thenReturn(tutoria);

        ResponseEntity<Tutoria> response = controller.addTutoria(tutoria);

        assertEquals(CREATED, response.getStatusCode());
        assertEquals(tutoria, response.getBody());
    }

    @Test
    void testAddTarea() {
        Tarea tarea = new Tarea();
        when(tareaService.createTarea(tarea)).thenReturn(tarea);

        ResponseEntity<Tarea> response = controller.addTarea(tarea);

        assertEquals(CREATED, response.getStatusCode());
        assertEquals(tarea, response.getBody());
    }

    @Test
    void testAddUsuario() {
        Usuario usuario = new Usuario();
        when(usuarioService.createUsuario(usuario)).thenReturn(usuario);

        ResponseEntity<Usuario> response = controller.addUsuario(usuario);

        assertEquals(CREATED, response.getStatusCode());
        assertEquals(usuario, response.getBody());
    }

    // === Tests para métodos DELETE ===

    @Test
    void testDeleteActa() {
        Acta acta = new Acta();
        when(actaService.deleteActa(acta)).thenReturn(acta);

        ResponseEntity<Acta> response = controller.deleteActa(acta);

        assertEquals(OK, response.getStatusCode());
        assertEquals(acta, response.getBody());
    }

    @Test
    void testDeleteAlumno() {
        Alumno alumno = new Alumno();
        when(alumnoService.deleteAlumno(alumno)).thenReturn(alumno);

        ResponseEntity<Alumno> response = controller.deleteAlumno(alumno);

        assertEquals(OK, response.getStatusCode());
        assertEquals(alumno, response.getBody());
    }

    @Test
    void testDeleteTutoria() {
        Tutoria tutoria = new Tutoria();
        when(tutoriaService.deleteTutoria(tutoria)).thenReturn(tutoria);

        ResponseEntity<Tutoria> response = controller.deleteTutoria(tutoria);

        assertEquals(OK, response.getStatusCode());
        assertEquals(tutoria, response.getBody());
    }

    @Test
    void testDeleteTarea() {
        Tarea tarea = new Tarea();
        when(tareaService.deleteTarea(tarea)).thenReturn(tarea);

        ResponseEntity<Tarea> response = controller.deleteTarea(tarea);

        assertEquals(OK, response.getStatusCode());
        assertEquals(tarea, response.getBody());
    }

    // === Tests para listar ===

    @Test
    void testCatalogActa() {
        List<Acta> lista = List.of(new Acta(), new Acta());
        when(actaService.findAll()).thenReturn(lista);

        List<Acta> result = controller.catalogActa(model);

        verify(model).addAttribute("actas", lista);
        assertEquals(lista, result);
    }

    @Test
    void testCatalogAlumno() {
        List<Alumno> lista = List.of(new Alumno(), new Alumno());
        when(alumnoService.findAll()).thenReturn(lista);

        List<Alumno> result = controller.catalogAlumno(model);

        verify(model).addAttribute("alumnos", lista);
        assertEquals(lista, result);
    }

    @Test
    void testCatalogTutorias() {
        List<Tutoria> lista = List.of(new Tutoria(), new Tutoria());
        when(tutoriaService.findAll()).thenReturn(lista);

        List<Tutoria> result = controller.catalogTutorias(model);

        verify(model).addAttribute("tutorias", lista);
        assertEquals(lista, result);
    }

    @Test
    void testCatalogTareas() {
        List<Tarea> lista = List.of(new Tarea(), new Tarea());
        when(tareaService.findAll()).thenReturn(lista);

        List<Tarea> result = controller.catalogTareas(model);

        verify(model).addAttribute("tareas", lista);
        assertEquals(lista, result);
    }

    // === Tests para get by id o key ===

    @Test
    void testGetActaByTutoriaId_Found() {
        Tutoria tutoria = new Tutoria();
        Acta acta = new Acta();
        tutoria.setActa(acta);

        when(tutoriaService.findByIdTutoria(1L)).thenReturn(Optional.of(tutoria));

        ResponseEntity<Acta> response = controller.getActaByTutoriaId(1L);

        assertEquals(OK, response.getStatusCode());
        assertEquals(acta, response.getBody());
    }

    @Test
    void testGetActaByTutoriaId_TutoriaNotFound() {
        when(tutoriaService.findByIdTutoria(1L)).thenReturn(Optional.empty());

        assertThrows(TutoriaNotFoundException.class, () -> controller.getActaByTutoriaId(1L));
    }

    @Test
    void testGetActaByTutoriaId_ActaNotFound() {
        Tutoria tutoria = new Tutoria();
        tutoria.setActa(null);

        when(tutoriaService.findByIdTutoria(1L)).thenReturn(Optional.of(tutoria));

        assertThrows(ActaNotFoundException.class, () -> controller.getActaByTutoriaId(1L));
    }

    @Test
    void testGetAlumnoByDni_Found() {
        Alumno alumno = new Alumno();
        when(alumnoService.findByDni("1234")).thenReturn(Optional.of(alumno));

        Alumno result = controller.getAlumnoByDni("1234", model);

        verify(model).addAttribute("alumno", alumno);
        assertEquals(alumno, result);
    }

    @Test
    void testGetAlumnoByDni_NotFound() {
        when(alumnoService.findByDni("1234")).thenReturn(Optional.empty());

        assertThrows(AlumnoNotFoundException.class, () -> controller.getAlumnoByDni("1234", model));
    }

    @Test
    void testGetTutoriaById_Found() {
        Tutoria tutoria = new Tutoria();
        when(tutoriaService.findByIdTutoria(2L)).thenReturn(Optional.of(tutoria));

        Tutoria result = controller.getTutoriaById(2L, model);

        verify(model).addAttribute("tutoria", tutoria);
        assertEquals(tutoria, result);
    }

    @Test
    void testGetTutoriaById_NotFound() {
        when(tutoriaService.findByIdTutoria(2L)).thenReturn(Optional.empty());

        assertThrows(TutoriaNotFoundException.class, () -> controller.getTutoriaById(2L, model));
    }

    @Test
    void testGetTareaById_Found() {
        Tarea tarea = new Tarea();
        when(tareaService.findByIdTarea(3L)).thenReturn(Optional.of(tarea));

        Tarea result = controller.getTareaById(3L, model);

        verify(model).addAttribute("tutoria", tarea);
        assertEquals(tarea, result);
    }

    @Test
    void testGetTareaById_NotFound() {
        when(tareaService.findByIdTarea(3L)).thenReturn(Optional.empty());

        assertThrows(TareaNotFoundException.class, () -> controller.getTareaById(3L, model));
    }

    // === Tests para update ===

    @Test
    void testUpdateActa() {
        Acta acta = new Acta();
        when(actaService.updateActa(acta)).thenReturn(acta);

        ResponseEntity<Acta> response = controller.updateActa(acta);

        assertEquals(CREATED, response.getStatusCode());
        assertEquals(acta, response.getBody());
    }

    @Test
    void testUpdateAlumno() {
        Alumno alumno = new Alumno();
        when(alumnoService.updateAlumno(alumno)).thenReturn(alumno);

        ResponseEntity<Alumno> response = controller.updateAlumno(alumno);

        assertEquals(CREATED, response.getStatusCode());
        assertEquals(alumno, response.getBody());
    }

    @Test
    void testUpdateTarea() {
        Tarea tarea = new Tarea();
        when(tareaService.updateTarea(tarea)).thenReturn(tarea);

        ResponseEntity<Tarea> response = controller.updateTarea(tarea);

        assertEquals(CREATED, response.getStatusCode());
        assertEquals(tarea, response.getBody());
    }

    @Test
    void testUpdateTutoria() {
        Tutoria tutoria = new Tutoria();
        when(tutoriaService.updateTutoria(tutoria)).thenReturn(tutoria);

        ResponseEntity<Tutoria> response = controller.updateTutoria(tutoria);

        assertEquals(CREATED, response.getStatusCode());
        assertEquals(tutoria, response.getBody());
    }

}
