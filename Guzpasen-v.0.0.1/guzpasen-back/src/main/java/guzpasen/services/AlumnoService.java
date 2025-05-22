package guzpasen.services;

import java.util.List;
import java.util.Optional;

import guzpasen.models.Alumno;

public interface AlumnoService {
	
	public Alumno createAlumno(Alumno alumno);
	
	public Alumno deleteAlumno(Alumno alumno);
	
	List<Alumno> findAll();
	
	Optional<Alumno> findByDni(String dni);
		
	public Alumno updateAlumno(Alumno alumno);
}
