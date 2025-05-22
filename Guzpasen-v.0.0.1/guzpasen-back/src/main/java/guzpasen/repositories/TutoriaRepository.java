package guzpasen.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import guzpasen.models.Tutoria;
import guzpasen.models.Tutoria.Estado;

@Repository
public interface TutoriaRepository extends JpaRepository<Tutoria, Long> {
	
	List<Tutoria> findAll();
	
	Optional<Tutoria> findByIdTutoria(Long idTutoria);
	
	List<Tutoria> findByEstado(Estado estado);
	
	List<Tutoria> findByFecha(LocalDate fecha);
	
	List<Tutoria> findByAsignatura(String asignatura);
	
}
