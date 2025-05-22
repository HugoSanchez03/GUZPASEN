package guzpasen.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import guzpasen.models.Tutoria;
import guzpasen.models.Tutoria.Estado;

public interface TutoriaService {
	
	public Tutoria createTutoria(Tutoria tutoria);
	
	public Tutoria deleteTutoria(Tutoria tutoria);
	
	List<Tutoria> findAll();
	
	Optional<Tutoria> findByIdTutoria(Long idTutoria);
	
	List<Tutoria> findByEstado(Estado estado);
	
	List<Tutoria> findByFecha(LocalDate fecha);
	
	List<Tutoria> findByAsignatura(String asignatura);
	
	public Tutoria updateTutoria(Tutoria tutoria);
}
