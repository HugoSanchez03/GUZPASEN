package guzpasen.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import guzpasen.exceptions.ActaNotFoundException;
import guzpasen.models.Acta;
import guzpasen.repositories.ActaRespository;

@Service
public class ActaServiceImpl implements ActaService {

	@Autowired
	private ActaRespository actaRespository;
	
	@Override
	public Acta createActa(Acta acta) {
		return actaRespository.save(acta);
	}

	@Override
	public Acta deleteActa(Acta acta) {
		actaRespository.delete(acta);
		return acta;
	}

	@Override
	public List<Acta> findAll() {
		return actaRespository.findAll();
	}

	@Override
	public Optional<Acta> findByIdActa(Long idActa) {
		return actaRespository.findByIdActa(idActa);
	}

	@Override
	public List<Acta> findByFecha(LocalDate fecha) {
		return actaRespository.findByFecha(fecha);
	}

	@Override
	public Acta updateActa(Acta acta) {
		Acta actaEncontrada = actaRespository.findById(acta.getIdActa()).orElseThrow( 
				() -> new ActaNotFoundException(acta.getIdActa()));
		
		actaEncontrada.setPuntosTratados(acta.getPuntosTratados());
		actaEncontrada.setObservaciones(acta.getObservaciones());
		actaEncontrada.setFecha(acta.getFecha());
		
		return actaRespository.save(actaEncontrada);

	}

}
