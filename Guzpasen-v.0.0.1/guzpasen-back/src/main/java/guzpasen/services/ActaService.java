package guzpasen.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import guzpasen.models.Acta;

public interface ActaService {
	
	public Acta createActa(Acta acta);
	
	public Acta deleteActa(Acta acta);
	
	List<Acta> findAll();
	
	Optional<Acta> findByIdActa(Long idActa);
		
	List<Acta> findByFecha(LocalDate fecha);
		
	public Acta updateActa(Acta acta);
}
