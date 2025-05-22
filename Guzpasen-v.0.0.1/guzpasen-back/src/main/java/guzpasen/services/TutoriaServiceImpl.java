package guzpasen.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import guzpasen.exceptions.TutoriaNotFoundException;
import guzpasen.models.Alumno;
import guzpasen.models.Tutoria;
import guzpasen.models.Tutoria.Estado;
import guzpasen.repositories.AlumnoRepository;
import guzpasen.repositories.TutoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class TutoriaServiceImpl implements TutoriaService {
	
	@Autowired
	private TutoriaRepository tutoriaRepository;
	
	@Autowired
	private AlumnoRepository alumnoRepository;
	
	@Override
	@Transactional
	public Tutoria createTutoria(Tutoria tutoria) {
	    String dni = tutoria.getAlumno().getDni();

	    Alumno alumno = alumnoRepository.findById(dni)
	        .orElseThrow(() -> new EntityNotFoundException("Alumno no encontrado con DNI: " + dni));

	    tutoria.setAlumno(alumno);

	    return tutoriaRepository.save(tutoria);
	}


	@Override
	public Tutoria deleteTutoria(Tutoria tutoria) {
		tutoriaRepository.delete(tutoria);
		return tutoria;
		
	}

	@Override
	public List<Tutoria> findAll() {
		return tutoriaRepository.findAll();
	}

	@Override
	public Optional<Tutoria> findByIdTutoria(Long idTutoria) {
		return tutoriaRepository.findByIdTutoria(idTutoria);
	}

	@Override
	public List<Tutoria> findByEstado(Estado estado) {
		return tutoriaRepository.findByEstado(estado);
	}

	@Override
	public List<Tutoria> findByFecha(LocalDate fecha) {
		return tutoriaRepository.findByFecha(fecha);
	}

	@Override
	public List<Tutoria> findByAsignatura(String asignatura) {
		return tutoriaRepository.findByAsignatura(asignatura);
	}

	@Override
	public Tutoria updateTutoria(Tutoria tutoria) {
	    Tutoria tutoriaEncontrada = tutoriaRepository.findById(tutoria.getIdTutoria())
	        .orElseThrow(() -> new TutoriaNotFoundException(tutoria.getIdTutoria()));

	    if (tutoria.getMotivo() != null && !tutoria.getMotivo().isEmpty()) {
	        tutoriaEncontrada.setMotivo(tutoria.getMotivo());
	    }
	    if (tutoria.getUrgencia() != null && !tutoria.getUrgencia().isEmpty()) {
	        tutoriaEncontrada.setUrgencia(tutoria.getUrgencia());
	    }
	    if (tutoria.getAsignatura() != null && !tutoria.getAsignatura().isEmpty()) {
	        tutoriaEncontrada.setAsignatura(tutoria.getAsignatura());
	    }
	    if (tutoria.getFecha() != null) {
	        tutoriaEncontrada.setFecha(tutoria.getFecha());
	    }
	    if (tutoria.getEstado() != null) {
	        tutoriaEncontrada.setEstado(tutoria.getEstado());
	    }
	    if (tutoria.getObservaciones() != null && !tutoria.getObservaciones().isEmpty()) {
	        tutoriaEncontrada.setObservaciones(tutoria.getObservaciones());
	    }
	    if (tutoria.getUsuario() != null) {
	        tutoriaEncontrada.setUsuario(tutoria.getUsuario());
	    }
	    if (tutoria.getAlumno() != null) {
	        tutoriaEncontrada.setAlumno(tutoria.getAlumno());
	    }
	    if (tutoria.getActa() != null) {
	        tutoriaEncontrada.setActa(tutoria.getActa());
	    }

	    return tutoriaRepository.save(tutoriaEncontrada);
	}

}
