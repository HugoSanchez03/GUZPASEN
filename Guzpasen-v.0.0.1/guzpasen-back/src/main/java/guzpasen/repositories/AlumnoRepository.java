package guzpasen.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import guzpasen.models.Alumno;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, String> {
		
	List<Alumno> findAll();
	
	Optional<Alumno> findByDni(String dni);
		
}
