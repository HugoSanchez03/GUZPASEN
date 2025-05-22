package guzpasen.exceptions;

public class AlumnoNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5030665213865363481L;

    public AlumnoNotFoundException() {
        super();
    }

    public AlumnoNotFoundException(String dni) {
        super("Alumno no encontrado: " + dni);
    }
}
