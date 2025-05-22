package guzpasen.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import guzpasen.models.Tarea;
import guzpasen.models.Tarea.Estado;

public interface TareaService {
	
	public Tarea createTarea(Tarea tarea);
	
	public Tarea deleteTarea(Tarea tarea);
	
	List<Tarea> findAll();
	
	Optional<Tarea> findByIdTarea(Long idTarea);
	
	List<Tarea> findByEstado(Estado estado);
	
	List<Tarea> findByFechaTarea(LocalDate fecha_tarea);
	
	List<Tarea> findByAsignatura(String asignatura);
	
	public Tarea updateTarea(Tarea tarea);
}
