package guzpasen.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import guzpasen.exceptions.TareaNotFoundException;
import guzpasen.models.Tarea;
import guzpasen.models.Tarea.Estado;
import guzpasen.repositories.TareaRepository;

@Service
public class TareaServiceImpl implements TareaService {

	@Autowired
	private TareaRepository tareaRepository;
	
	@Override
	public Tarea createTarea(Tarea tarea) {
		return tareaRepository.save(tarea);
	}

	@Override
	public Tarea deleteTarea(Tarea tarea) {
		tareaRepository.delete(tarea);
		return tarea;
	}

	@Override
	public List<Tarea> findAll() {
		return tareaRepository.findAll();
	}

	@Override
	public Optional<Tarea> findByIdTarea(Long idTarea) {
		return tareaRepository.findByIdTarea(idTarea);
	}

	@Override
	public List<Tarea> findByEstado(Estado estado) {
		return tareaRepository.findByEstado(estado);
	}

	@Override
	public List<Tarea> findByFechaTarea(LocalDate fecha_tarea) {
		return tareaRepository.findByFechaTarea(fecha_tarea);
	}

	@Override
	public List<Tarea> findByAsignatura(String asignatura) {
		return tareaRepository.findByAsignatura(asignatura);
	}

	@Override
	public Tarea updateTarea(Tarea tarea) {
		Tarea tareaEncontrada = tareaRepository.findById(tarea.getIdTarea()).orElseThrow( 
				() -> new TareaNotFoundException(tarea.getIdTarea()));
		
		tareaEncontrada.setAsignatura(tarea.getAsignatura());
		tareaEncontrada.setDescripcion(tarea.getDescripcion());
		tareaEncontrada.setEstado(tarea.getEstado());
		tareaEncontrada.setFechaTarea(tarea.getFechaTarea());
		tareaEncontrada.setTutoria(tarea.getTutoria());
	
		return tareaRepository.save(tareaEncontrada);
		
	}

}
