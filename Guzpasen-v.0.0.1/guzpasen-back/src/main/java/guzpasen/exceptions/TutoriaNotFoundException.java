package guzpasen.exceptions;

public class TutoriaNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5030665213865363481L;

    public TutoriaNotFoundException() {
        super();
    }

    public TutoriaNotFoundException(Long idTutoria) {
        super("Tutoria no encontrada: " + idTutoria);
    }
}