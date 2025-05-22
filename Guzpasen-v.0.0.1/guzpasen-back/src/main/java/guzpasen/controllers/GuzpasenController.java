package guzpasen.controllers;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

/**
 * Controlador REST para gestionar las operaciones CRUD sobre actas, alumnos, tareas, tutorías y usuarios.
 */
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/guzpasen")
public class GuzpasenController {

    @Autowired
    private ActaService actaService;

    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private TareaService tareaService;

    @Autowired
    private TutoriaService tutoriaService;

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private JwtService jwtService;

    // SERVICIOS CREATE

    /**
     * Añade una nueva acta.
     * @param acta Acta a añadir.
     * @return La acta creada con código HTTP 201.
     */
    @PostMapping("/anadirActa")
    public ResponseEntity<Acta> addActa(@RequestBody Acta acta) {
        System.out.println("Recibido acta: " + acta);
        if (acta.getFecha() == null) {
            acta.setFecha(LocalDate.now());
        }
        Acta actaAnadida = actaService.createActa(acta);
        System.out.println("Guardado acta: " + actaAnadida);
        return new ResponseEntity<>(actaAnadida, HttpStatus.CREATED);
    }

    /**
     * Añade un nuevo alumno.
     * @param alumno Alumno a añadir.
     * @return El alumno creado con código HTTP 201.
     */
    @PostMapping("/anadirAlumno")
    public ResponseEntity<Alumno> addAlumno(@RequestBody Alumno alumno) {
        Alumno alumnoAnadido = alumnoService.createAlumno(alumno);
        return new ResponseEntity<>(alumnoAnadido, HttpStatus.CREATED);
    }

    /**
     * Añade una nueva tutoría.
     * @param tutoria Tutoría a añadir.
     * @return La tutoría creada con código HTTP 201.
     */
    @PostMapping("/anadirTutoria")
    public ResponseEntity<Tutoria> addTutoria(@RequestBody Tutoria tutoria) {
        Tutoria tutoriaAnadida = tutoriaService.createTutoria(tutoria);
        return new ResponseEntity<>(tutoriaAnadida, HttpStatus.CREATED);
    }

    /**
     * Añade una nueva tarea.
     * @param tarea Tarea a añadir.
     * @return La tarea creada con código HTTP 201.
     */
    @PostMapping("/anadirTarea")
    public ResponseEntity<Tarea> addTarea(@RequestBody Tarea tarea) {
        Tarea tareaAnadida = tareaService.createTarea(tarea);
        return new ResponseEntity<>(tareaAnadida, HttpStatus.CREATED);
    }

    /**
     * Añade un nuevo usuario.
     * @param usuario Usuario a añadir.
     * @return El usuario creado con código HTTP 201.
     */
    @PostMapping("/anadirUsuario")
    public ResponseEntity<Usuario> addUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioAnadido = usuarioService.createUsuario(usuario);
        return new ResponseEntity<>(usuarioAnadido, HttpStatus.CREATED);
    }

    // SERVICIOS DELETE

    /**
     * Elimina una acta.
     * @param acta Acta a eliminar.
     * @return La acta eliminada con código HTTP 200.
     */
    @DeleteMapping("/eliminarActa")
    public ResponseEntity<Acta> deleteActa(@RequestBody Acta acta) {
        Acta actaEliminado = actaService.deleteActa(acta);
        return new ResponseEntity<>(actaEliminado, HttpStatus.OK);
    }

    /**
     * Elimina un alumno.
     * @param alumno Alumno a eliminar.
     * @return El alumno eliminado con código HTTP 200.
     */
    @DeleteMapping("/eliminarAlumno")
    public ResponseEntity<Alumno> deleteAlumno(@RequestBody Alumno alumno) {
        Alumno alumnoEliminado = alumnoService.deleteAlumno(alumno);
        return new ResponseEntity<>(alumnoEliminado, HttpStatus.OK);
    }

    /**
     * Elimina una tutoría.
     * @param tutoria Tutoría a eliminar.
     * @return La tutoría eliminada con código HTTP 200.
     */
    @DeleteMapping("/eliminarTutoria")
    public ResponseEntity<Tutoria> deleteTutoria(@RequestBody Tutoria tutoria) {
        Tutoria tutoriaEliminada = tutoriaService.deleteTutoria(tutoria);
        return new ResponseEntity<>(tutoriaEliminada, HttpStatus.OK);
    }

    /**
     * Elimina una tarea.
     * @param tarea Tarea a eliminar.
     * @return La tarea eliminada con código HTTP 200.
     */
    @DeleteMapping("/eliminarTarea")
    public ResponseEntity<Tarea> deleteTarea(@RequestBody Tarea tarea) {
        Tarea tareaEliminada = tareaService.deleteTarea(tarea);
        return new ResponseEntity<>(tareaEliminada, HttpStatus.OK);
    }

    // SERVICIOS DE CONSULTA

    /**
     * Lista todas las actas registradas.
     * @param model Modelo para vista.
     * @return Lista de actas.
     */
    @RequestMapping("/listarActas")
    public List<Acta> catalogActa(Model model) {
        List<Acta> actas = actaService.findAll();
        model.addAttribute("actas", actas);
        return actas;
    }

    /**
     * Busca una acta por su ID.
     * @param id ID del acta.
     * @param model Modelo para vista.
     * @return La acta encontrada.
     * @throws ActaNotFoundException si no se encuentra el acta.
     */
    @GetMapping("/tutoria/{idTutoria}/acta")
    public ResponseEntity<Acta> getActaByTutoriaId(@PathVariable Long idTutoria) {
        Tutoria tutoria = tutoriaService.findByIdTutoria(idTutoria)
            .orElseThrow(() -> new TutoriaNotFoundException(idTutoria));

        Acta acta = tutoria.getActa();
        if (acta == null) {
            throw new ActaNotFoundException(idTutoria);
        }

        return ResponseEntity.ok(acta);
    }


    /**
     * Lista todos los alumnos registrados.
     * @param model Modelo para vista.
     * @return Lista de alumnos.
     */
    @RequestMapping("/listarAlumnos")
    public List<Alumno> catalogAlumno(Model model) {
        List<Alumno> alumnos = alumnoService.findAll();
        model.addAttribute("alumnos", alumnos);
        return alumnos;
    }

    /**
     * Busca un alumno por su DNI.
     * @param dni DNI del alumno.
     * @param model Modelo para vista.
     * @return El alumno encontrado.
     * @throws AlumnoNotFoundException si no se encuentra el alumno.
     */
    @GetMapping("/alumnoPorDni/{dni}")
    public Alumno getAlumnoByDni(@PathVariable String dni, Model model) {
        Alumno alumno = alumnoService.findByDni(dni)
                .orElseThrow(() -> new AlumnoNotFoundException(dni));
        model.addAttribute("alumno", alumno);
        return alumno;
    }

    /**
     * Lista todas las tutorías registradas.
     * @param model Modelo para vista.
     * @return Lista de tutorías.
     */
    @RequestMapping("/listarTutorias")
    public List<Tutoria> catalogTutorias(Model model) {
        List<Tutoria> tutorias = tutoriaService.findAll();
        model.addAttribute("tutorias", tutorias);
        return tutorias;
    }

    /**
     * Busca una tutoría por su ID.
     * @param id_tutoria ID de la tutoría.
     * @param model Modelo para vista.
     * @return La tutoría encontrada.
     * @throws TutoriaNotFoundException si no se encuentra la tutoría.
     */
    @GetMapping("/tutoriaPorId/{id_tutoria}")
    public Tutoria getTutoriaById(@PathVariable Long id_tutoria, Model model) {
        Tutoria tutoria = tutoriaService.findByIdTutoria(id_tutoria)
                .orElseThrow(() -> new TutoriaNotFoundException(id_tutoria));
        model.addAttribute("tutoria", tutoria);
        return tutoria;
    }

    /**
     * Lista todas las tareas registradas.
     * @param model Modelo para vista.
     * @return Lista de tareas.
     */
    @RequestMapping("/listarTareas")
    public List<Tarea> catalogTareas(Model model) {
        List<Tarea> tareas = tareaService.findAll();
        model.addAttribute("tareas", tareas);
        return tareas;
    }

    /**
     * Busca una tarea por su ID.
     * @param id_tarea ID de la tarea.
     * @param model Modelo para vista.
     * @return La tarea encontrada.
     * @throws TareaNotFoundException si no se encuentra la tarea.
     */
    @GetMapping("/tareaPorId/{id_tarea}")
    public Tarea getTareaById(@PathVariable Long id_tarea, Model model) {
        Tarea tarea = tareaService.findByIdTarea(id_tarea)
                .orElseThrow(() -> new TareaNotFoundException(id_tarea));
        model.addAttribute("tutoria", tarea);
        return tarea;
    }

    // SERVICIOS DE UPDATE

    /**
     * Modifica una acta existente.
     * @param acta Acta con los datos modificados.
     * @return Acta actualizada con código HTTP 201.
     */
    @PutMapping("/modificarActa")
    public ResponseEntity<Acta> updateActa(@RequestBody Acta acta) {
        return new ResponseEntity<>(actaService.updateActa(acta), HttpStatus.CREATED);
    }

    /**
     * Modifica un alumno existente.
     * @param alumno Alumno con los datos modificados.
     * @return Alumno actualizado con código HTTP 201.
     */
    @PutMapping("/modificarAlumno")
    public ResponseEntity<Alumno> updateAlumno(@RequestBody Alumno alumno) {
        return new ResponseEntity<>(alumnoService.updateAlumno(alumno), HttpStatus.CREATED);
    }

    /**
     * Modifica una tarea existente.
     * @param tarea Tarea con los datos modificados.
     * @return Tarea actualizada con código HTTP 201.
     */
    @PutMapping("/modificarTarea")
    public ResponseEntity<Tarea> updateTarea(@RequestBody Tarea tarea) {
        return new ResponseEntity<>(tareaService.updateTarea(tarea), HttpStatus.CREATED);
    }

    /**
     * Modifica una tutoría existente.
     * @param tutoria Tutoría con los datos modificados.
     * @return Tutoría actualizada con código HTTP 201.
     */
    @PutMapping("/modificarTutoria")
    public ResponseEntity<Tutoria> updateTutoria(@RequestBody Tutoria tutoria) {
        return new ResponseEntity<>(tutoriaService.updateTutoria(tutoria), HttpStatus.CREATED);
    }
    
    /**
     * Verifica que el usuario y la contraseña son correctos.
     * @param nick
     * @param clave
     * @return
     */
	    @PostMapping("/login")
	    public ResponseEntity<?> login(@RequestParam String nick, @RequestParam String clave) {
	        System.out.println("nick recibido: " + nick);
	        System.out.println("clave recibida: " + clave);
	        Optional<Usuario> userOpt = usuarioRepo.findByNick(nick);
	
	        if (userOpt.isPresent() && userOpt.get().getClave().equals(clave)) {
	            String token = jwtService.generateToken(nick);
	            return ResponseEntity.ok().body(Collections.singletonMap("token", token));
	        } else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
	        }
	    }

}
