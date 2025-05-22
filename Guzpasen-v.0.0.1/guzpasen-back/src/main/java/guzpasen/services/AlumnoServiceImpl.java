package guzpasen.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import guzpasen.exceptions.AlumnoNotFoundException;
import guzpasen.models.Alumno;
import guzpasen.repositories.AlumnoRepository;

@Service
public class AlumnoServiceImpl implements AlumnoService {

	@Autowired
	private AlumnoRepository alumnoRepository;

	
	@Override
	public Alumno createAlumno(Alumno alumno) {
		return alumnoRepository.save(alumno);
	}

	@Override
	public Alumno deleteAlumno(Alumno alumno) {
		alumnoRepository.delete(alumno);
		return alumno;
	}

	@Override
	public List<Alumno> findAll() {
		return alumnoRepository.findAll();
	}

	@Override
	public Optional<Alumno> findByDni(String dni) {
		return alumnoRepository.findByDni(dni);
	}

	@Override
	public Alumno updateAlumno(Alumno alumno) {
		Alumno alumnoEncontrado = alumnoRepository.findById(alumno.getDni()).orElseThrow( 
				() -> new AlumnoNotFoundException(alumno.getDni()));
		
		alumnoEncontrado.setDni(alumno.getDni());
		alumnoEncontrado.setNombre(alumno.getNombre());
		alumnoEncontrado.setApellidos(alumno.getApellidos());
		alumnoEncontrado.setNombreTutorLegal(alumno.getNombreTutorLegal());
		alumnoEncontrado.setApellidosTutorLegal(alumno.getApellidosTutorLegal());
		alumnoEncontrado.setEmailTutorLegal(alumno.getEmailTutorLegal());
		
		return alumnoRepository.save(alumnoEncontrado);
	}

}
