package guzpasen.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import guzpasen.models.Tarea;
import guzpasen.models.Tarea.Estado;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {
		
	List<Tarea> findAll();
	
	Optional<Tarea> findByIdTarea(Long idTarea);
	
	List<Tarea> findByEstado(Estado estado);
	
	List<Tarea> findByFechaTarea(LocalDate fecha_tarea);
	
	List<Tarea> findByAsignatura(String asignatura);
	
}
