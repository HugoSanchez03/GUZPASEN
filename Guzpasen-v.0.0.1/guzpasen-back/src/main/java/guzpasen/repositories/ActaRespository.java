package guzpasen.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import guzpasen.models.Acta;

@Repository
public interface ActaRespository extends JpaRepository<Acta, Long> {
			
	List<Acta> findAll();
	
	Optional<Acta> findByIdActa(Long idActa);
		
	List<Acta> findByFecha(LocalDate fecha);
		
}
